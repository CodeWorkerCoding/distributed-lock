package com.edu.nchu.distriuted.Interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 项目拦截器配置
 * Created by fujianjian on 2016/11/13.
 */
public class WebAppConfig extends WebMvcConfigurerAdapter {

    @Autowired
    DistributedInterceptor distributedInterceptor;

    /***
     * 配置拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(distributedInterceptor);
        interceptorRegistration.addPathPatterns("/***");
    }
}
