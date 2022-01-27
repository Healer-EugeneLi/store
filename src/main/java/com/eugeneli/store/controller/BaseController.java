package com.eugeneli.store.controller;

import com.eugeneli.store.controller.ex.*;
import com.eugeneli.store.service.ex.*;
import com.eugeneli.store.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

public class BaseController {

    public final static int ok=200;

    @ExceptionHandler({ServiceException.class, FileUploadException.class})
    public JsonResult<Void> handleException(Throwable e){

        JsonResult<Void> jsonResult=new JsonResult<>(e);

        if(e instanceof UsernameDuplicatedException){
            jsonResult.setState(4000);
            jsonResult.setMessage("用户名已经被占用");
        } else if (e instanceof InsertException){
            jsonResult.setState(5000);
            jsonResult.setMessage("用户插入时出现异常");
        }else if(e instanceof UsernameNotFoundException){
            jsonResult.setState(5001);
            jsonResult.setMessage("用户不存在异常");
        }else if(e instanceof PasswordNotMatchException){
            jsonResult.setState(5002);
            jsonResult.setMessage("密码错误异常");
        }else if (e instanceof UpdateException){
            jsonResult.setState(5004);
            jsonResult.setMessage("更新数据时产生未知的异常");
        }else if (e instanceof FileEmptyException){
            jsonResult.setState(6001);
            jsonResult.setMessage("文件为空的异常");
        }else if (e instanceof FileSizeException){
            jsonResult.setState(6002);
            jsonResult.setMessage("文件大小超出限制的异常");
        }else if (e instanceof FileTypeException){
            jsonResult.setState(6003);
            jsonResult.setMessage("文件类型出错异常");
        }else if (e instanceof FileIOException){
            jsonResult.setState(6004);
            jsonResult.setMessage("文件读取出现异常");
        }
        return jsonResult;

    }

    //封装session对象中数据的获取、数据的设置 当用户登录成功后进行数据的设置 设置到全局的session对象


    /**
     * 获取session对象中的uid
     * @param session session对象
     * @return 当前登录的用户uid的值
     */
    public final Integer getUidFromSession(HttpSession session){

        return Integer.valueOf(session.getAttribute("uid").toString());

    }

    /**
     * 获取当前登录用户的username 从session对象中获取
     * @param session
     * @return
     */
    public final String getUsernameFromSession(HttpSession session){

        return session.getAttribute("username").toString();
    }
}
