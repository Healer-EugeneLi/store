package com.eugeneli.store.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: LoginInterceptor
 * @Description:
 * @Author EugeneLi
 * @Date: 2022/1/22
 * @Time: 1:08
 */

public class LoginInterceptor implements HandlerInterceptor {

    //在调用处理请求的方法之前 被自动调用的方法

    /**
     * 检测全局session对象中是否有uid对象 如果有则放行  没有则重定向到登录
     * @param request 请求对象
     * @param response 响应对象
     * @param handler 处理器 url+Controller 映射
     * @return 如果返回值为true表示放行当前的请求 如果返回值为false则表示拦截当前的请求
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Object uid = request.getSession().getAttribute("uid");
        if (uid==null){
            //用户没有登录过系统 则重定向到login.html
            response.sendRedirect("/web/login.html");
            //结束后续的调用
            return false;
        }
        //请求放行
        return true;
    }

    //在ModelAndView对象返回之后被调用
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    //在整个请求所有关联的资源被执行完毕最后执行的方法
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
