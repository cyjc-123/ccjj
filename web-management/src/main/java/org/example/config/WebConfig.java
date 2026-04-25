package org.example.config;

import org.example.interceptor.DemoIntercepter;
import org.example.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


//@Configuration//配置类
public class WebConfig implements WebMvcConfigurer {
//    @Autowired
//    private DemoIntercepter demoIntercepter;
    //@Autowired
    private TokenInterceptor tokenInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor )
                .addPathPatterns("/**").excludePathPatterns("/login");
    }
}
