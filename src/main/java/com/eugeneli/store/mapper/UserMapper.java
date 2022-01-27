package com.eugeneli.store.mapper;


import com.eugeneli.store.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import javax.annotation.ManagedBean;
import java.util.Date;

@Mapper
public interface UserMapper {

    /**
     * 用户注册时插入用户
     * 返回值为影响行数
     * 当用户插入成功返回1 否则返回0
     * @param user
     * @return
     */
    Integer insert(User user);

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    User findUserByUsername(String username);

    /**
     * 根据用户的uid更新用户的密码
     * @param uid 用户id
     * @param password 用户要修改的密码
     * @param modifiedUser 修改的执行者
     * @param modifiedTime 修改数据的时间
     * @return 返回值受影响的行数
     */
    Integer updatePasswordByUid(Integer uid,
                                String password,
                                String modifiedUser,
                                Date modifiedTime);

    /**
     * 根据用户的id查询用户的数据
     * @param uid
     * @return 如果找到则返回对象 反之返回null值
     */
    User findByUid(Integer uid);


    /**
     * 更新用户信息
     * @param user
     * @return
     */
    Integer updateInfoByUid(User user);

    /**
     * 根据用户id上传头像
     * @param uid
     * @param avatar
     * @param modifiedUser
     * @param modifiedTime
     * @return
     */
    Integer updateAvatarByUid(@Param("uid") Integer uid,
                              @Param("avatar") String avatar,
                              @Param("modifiedUser") String modifiedUser,
                              @Param("modifiedTime") Date modifiedTime);
}
