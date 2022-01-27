package com.eugeneli.store.service.impl;

import com.eugeneli.store.entity.User;
import com.eugeneli.store.mapper.UserMapper;
import com.eugeneli.store.service.IUserService;
import com.eugeneli.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

@Service//交给spring管理
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;
    /**
     * 注册用户
     *
     * @param user
     */
    @Override
    public void reg(User user) {

        String username=user.getUsername();

        //1.判断是否已有同名的用户
        if (userMapper.findUserByUsername(username)!=null){
            throw new UsernameDuplicatedException("用户名已存在");
        }else {
            //2.用户名不存在 可以注册

            //获取密码
            String password = user.getPassword();
            //随机生成一个盐值salt
            String salt = UUID.randomUUID().toString().toUpperCase();
            //通过密码+盐值 去生成一个md5加密的串
            String md5 = getMd5(password, salt);

            //3.设置相关信息

            user.setPassword(md5);
            user.setSalt(salt);

            user.setIsDelete(0);

            //生成时间
            Date now=new Date();
            user.setCreatedUser(username);
            user.setCreatedTime(now);
            user.setModifiedUser(username);
            user.setModifiedTime(now);

            Integer result = userMapper.insert(user);
            if (result!=1)
                throw new InsertException("添加用户数据时出现未知错误，请联系系统管理员");

        }

    }

    /**
     * 用户登录
     *
     * @param username
     * @param password
     */
    @Override
    public User login(String username, String password) {

        User user = userMapper.findUserByUsername(username);
        //1.判断用户是否存在 状态码isDelete
        if(user==null||user.getIsDelete()==1)
            throw new UsernameNotFoundException("用户不存在");

        //2.获取盐值 加上password进行加密 判断是否与数据库中加密过的密码一致
        String salt = user.getSalt();
        String md5 = getMd5(password, salt);
        if (!user.getPassword().equals(md5))
            throw new PasswordNotMatchException("密码错误");

        //3.符合条件之后 创建新对象 封装信息
        User resultUser=new User();
        resultUser.setUid(user.getUid());
        resultUser.setUsername(user.getUsername());
        resultUser.setAvatar(user.getAvatar());

        return resultUser;
    }

    /**
     * 用户修改密码
     *
     * @param uid
     * @param username
     * @param oldPassword
     * @param newPassword
     */
    @Override
    public void changePassword(Integer uid, String username, String oldPassword, String newPassword) {


        //1.先判断用户存不存在
        User result = userMapper.findByUid(uid);
        if (result==null||result.getIsDelete()==1)
            throw new UsernameNotFoundException("用户数据不存在");

        //2.原密码和数据库密码比较
        String oldMd5Password=getMd5(oldPassword, result.getSalt());

        if (!result.getPassword().equals(oldMd5Password)){
            throw new PasswordNotMatchException("密码错误");
        }
        //3.将新密码加密
        String newMd5Password=getMd5(newPassword, result.getSalt());
        Integer rows = userMapper.updatePasswordByUid(uid, newMd5Password, username, new Date());

        if (rows!=1)
            throw new UpdateException("更新数据产生未知的异常");


    }

    /**
     * 根据用户id获取用户信息 加载在个人资料上
     *
     * @param uid
     * @return 只返回部分信息 phone email gender 显示在个人资料上
     */
    @Override
    public User getUserByUid(Integer uid) {

        //1.先判断用户数据存不存在
        User result = userMapper.findByUid(uid);
        if (result==null||result.getIsDelete()==1){
            throw new UsernameNotFoundException("用户数据不存在");
        }

        //2.封装需要的数据 减少数据的传输量
        User user=new User();
        user.setUsername(result.getUsername());
        user.setPhone(result.getPhone());
        user.setEmail(result.getEmail());
        user.setGender(result.getGender());
        return user;
    }


    /**
     * 更新用户信息
     *
     * @param uid      用户的id
     * @param username 用户名
     * @param user     用户信息
     */
    @Override
    public void changeInfo(Integer uid, String username, User user) {

        //1.先获取用户信息存不存在
        User result = userMapper.findByUid(uid);
        if (result==null||result.getIsDelete()==1)
            throw new UsernameNotFoundException("用户数据不存在");

        //2.因为个人资料里面传过来的user只有email phone gender 需要再封装一些信息
        user.setUid(uid);
        user.setModifiedUser(username);
        user.setModifiedTime(new Date());

        Integer rows = userMapper.updateInfoByUid(user);
        if (rows!=1){
            throw new UpdateException("更新用户资料时产生未知的异常");
        }
    }

    /**
     * 更新头像
     *
     * @param uid
     * @param username
     * @param avatar
     */
    @Override
    public void updateAvatarByUid(Integer uid, String username, String avatar) {

        //1.判断用户数据是否存在
        User user = userMapper.findByUid(uid);
        if (user==null||user.getIsDelete()==1)
            throw  new UsernameNotFoundException("用户数据不存在");

        //2.修改时间
        Date date=new Date();
        Integer rows = userMapper.updateAvatarByUid(uid, avatar, username, date);
        if (rows!=1)
            throw new UpdateException("更新头像时发生异常");


    }

    public String getMd5(String password, String salt){

        //加密三次
        for (int i=0;i<3;i++){
            password= DigestUtils.md5DigestAsHex((salt+password+salt).getBytes()).toUpperCase();
        }
        return password;
    }
}
