package com.eugeneli.store.config;

import com.eugeneli.store.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: LoginInterceptorConfigurer
 * @Description:
 * @Author EugeneLi
 * @Date: 2022/1/22
 * @Time: 1:17
 *
 * 处理器拦截器的注册
 */
@Configuration//加载当前的拦截器 并进行注册
public class LoginInterceptorConfigurer implements WebMvcConfigurer {



    /**
     * 将自定义拦截器进行注册 配置拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //创建自定义的拦截对象
        HandlerInterceptor interceptor=new LoginInterceptor();

        //白名单 放行
        List<String> patterns=new ArrayList<>();
        patterns.add("/bootstrap3/**");
        patterns.add("/css/**");
        patterns.add("/images/**");
        patterns.add("/js/**");
        patterns.add("/web/register.html");
        patterns.add("/web/index.html");
        patterns.add("/web/login.html");
        patterns.add("/web/product.html");
        patterns.add("/users/reg");
        patterns.add("/users/login");

        //完成拦截器的注册
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(interceptor);

        interceptorRegistration.addPathPatterns("/**")//表示要拦截的url
                                .excludePathPatterns(patterns);

    }
}
