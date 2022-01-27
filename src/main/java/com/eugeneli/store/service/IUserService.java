package com.eugeneli.store.service;

import com.eugeneli.store.entity.User;

/**
 * 处理用户数据的业务层接口
 * 1.仅以操作成功为前提来设计返回值类型，不考虑操作失败的情况；
 * 2.方法名称可以自定义，通常与用户操作的功能相关；
 * 3.方法的参数列表根据执行的具体业务功能来确定，需要哪些数据就设计哪些数据。
 * 通常情况下，参数需要足 以调用持久层对应的相关功能；同时还要满足参数是客户端可以传递给控制器的；
 * 4.方法中使用抛出异常的方式来表示操作失败。
 */
public interface IUserService {

    /**
     * 注册用户
     * @param user
     */
    public void reg(User user);

    /**
     * 用户登录
     */
    public User login(String username,String password);


    /**
     * 用户修改密码
     * @param uid
     * @param username
     * @param oldPassword
     * @param newPassword
     */
    public void changePassword(Integer uid,
                               String username,
                               String oldPassword,
                               String newPassword);

    /**
     * 根据用户id获取用户信息
     * @param uid
     * @return
     */
    public User getUserByUid(Integer uid);


    /**
     * 更新用户信息
     * @param uid 用户的id
     * @param username 用户名
     * @param user 用户信息
     */
    public void changeInfo(Integer uid,String username,User user);


    /**
     * 更新头像
     * @param uid
     * @param username
     * @param avatar
     */
    public void updateAvatarByUid(Integer uid,String username,String avatar);


}
