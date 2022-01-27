package com.eugeneli.store.controller;


import com.eugeneli.store.controller.ex.*;
import com.eugeneli.store.entity.User;
import com.eugeneli.store.service.IUserService;
import com.eugeneli.store.service.ex.InsertException;
import com.eugeneli.store.service.ex.UsernameDuplicatedException;
import com.eugeneli.store.util.JsonResult;
import jdk.nashorn.internal.runtime.regexp.JoniRegExp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import sun.security.x509.AVA;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@RestController
@RequestMapping("users")
public class UserController extends BaseController {


    @Autowired
    private IUserService userService;

    /*
    * 1.接收数据方式：请求处理方法的参数列表设置为pojo类型来接收前端的数据，
    * SpringBoot会将前端的url地址中的参数名和pojo类的属性名进行比较，如果这两个名称相同，则将值注入到pojo类的属性上
    *
    * 2.接收数据方式：请求处理方法的参数列表设置为非pojo类型
    * SpringBoot会直接将请求的参数名和方法的参数名直接进行比较，如果名称相同则自动完成值的依赖注入
    *
    * */


    @RequestMapping("reg")
    public JsonResult<Void> reg(User user){

        userService.reg(user);
        return new JsonResult<>(ok);
    }

    /**
     * 在登录的方法中将数据封装在session对象中 服务器本身自动创建有session对象 已经是一个全局的session对象
     *
     * springboot中会直接将请求的参数名和方法的参数名 进行比较 如果名称相同 则自动完成值的依赖注入
     */
    @PostMapping("login")
    public JsonResult<User> login(String username,
                                  String password,
                                  HttpSession session){

        User user = userService.login(username, password);
        //向session中完成数据的绑定 session是全局的
        session.setAttribute("uid",user.getUid());
        session.setAttribute("username",user.getUsername());

        //获取session中绑定的数据
        System.out.println(getUidFromSession(session));
        System.out.println(getUsernameFromSession(session));

        return new JsonResult<>(ok,user);
    }


    @PostMapping("changePassword")
    JsonResult<Void> changePassword(String oldPassword,
                                    String newPassword,
                                    HttpSession session){

        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changePassword(uid,username,oldPassword,newPassword);

        return new JsonResult<>(ok);

    }

    /**
     * 通过uid返回用户信息
     * @param session
     * @return
     */
    @RequestMapping("getByUid")
    public JsonResult<User> getUserByUid(HttpSession session){

        Integer uid = getUidFromSession(session);
        User data = userService.getUserByUid(uid);
//        System.out.println("获取的用户信息为"+data);
        return new JsonResult<>(ok,data);
    }

    /**
     * 修改用户资料
     * @param user
     * @param session
     * @return
     */
    @PostMapping("changeInfo")
    public JsonResult<Void> changeInfo(User user,
                                       HttpSession session){

        System.out.println("前端传过来的user"+user);
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);

        userService.changeInfo(uid,username,user);

        return new JsonResult<>(ok);
    }


    //设置文件上传相关限制信息
    //限制上传的图像大小10MB
    public static final Integer AVATAR_FILE_SIZE=10*1024*1024;

    public static final List<String> AVATAR_TYPES=new ArrayList<>();

    //允许上传的文件类型
    static {
        AVATAR_TYPES.add("image/jpeg");
        AVATAR_TYPES.add("image/png");
        AVATAR_TYPES.add("image/bmp");
        AVATAR_TYPES.add("image/gif");
    }
    /**
     * 上传用户头像
     * @param file
     * @param session
     * @return
     */
    @PostMapping("uploadAvatar")
    public JsonResult<String> uploadAvatar(@RequestParam("file")MultipartFile file,
                                           HttpSession session){

        //文件为空
        if (file.isEmpty()){
            throw new FileEmptyException();
        }

        //文件超出大小
        if (file.getSize()>AVATAR_FILE_SIZE){
            throw new FileSizeException();
        }

        //文件类型判断
        if (!AVATAR_TYPES.contains(file.getContentType())){
            throw new FileTypeException();
        }

        //获取当前项目的绝对磁盘路径
        String parent = session.getServletContext().getRealPath("upload");

        File dir=new File(parent);
        //不存在创建保存头像的文件夹
        if (!dir.exists()){
            dir.mkdirs();
        }

        //保存头像的文件名
        String fileType="";
        String originalFilename = file.getOriginalFilename();
        int beginIndex = originalFilename.lastIndexOf(".");
        if (beginIndex>0){
            fileType=originalFilename.toString().substring(beginIndex);
        }
        String fileName = UUID.randomUUID().toString()+fileType;

        //创建文件对象 保存文件
        File dest=new File(dir,fileName);

        try {
            file.transferTo(dest);
        }catch (IllegalStateException e){
            throw new FileStateException("文件状态异常");
        }catch (IOException e) {
            throw new FileIOException("文件上传时出现传输异常，请稍后重新尝试");

        }

        //文件路径
        String avatarPath="/upload/"+fileName;
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);

        userService.updateAvatarByUid(uid,username,avatarPath);

        return new JsonResult<>(ok,avatarPath);

    }
}
