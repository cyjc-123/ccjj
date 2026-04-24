package org.example.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
//@WebFilter("/*")//拦截所有请求
public class DempFilter implements Filter {
    //初始化方法，在服务器启动时执行一次
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        log.info("初始化方法");
    }
   //拦截到请求后 执行，执行多次
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
   log.info("拦截到请求");
   //放行
   filterChain.doFilter(servletRequest,servletResponse);
    }
   //销毁方法，在服务器关闭时执行一次
    @Override
    public void destroy() {
        log.info("销毁方法");
        Filter.super.destroy();
    }
}
