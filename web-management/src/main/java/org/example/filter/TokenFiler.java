package org.example.filter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.utils.CurrentHolder;
import org.example.utils.JwtUtils;

import java.io.IOException;
@Slf4j
@WebFilter("/*")
public class TokenFiler implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response= (HttpServletResponse) servletResponse;
        //获取请求路径
        String path=request.getRequestURI();// /emp/list
        //判断是否登录请求，登录请求放行，剩余的请求进行token校验
        if (path.contains("login")){
            log.info("登录请求");
            filterChain.doFilter(request,response);
            return;
        }
        //获取token
        String token=request.getHeader("token");
        //校验token，验证通过，放行，验证失败，返回错误信息
       if (token==null|| token.length()==0){
           log.info("token为空");
           response.setStatus(401);
           return;
       }
       try {
           Claims claims = JwtUtils.parseJWT(token);
           Integer empid = Integer.valueOf(claims.get("id").toString());
           CurrentHolder.setCurrentId(empid);
           log.info("当前登录员工id:{}，存入ThreadLocal",empid);
       }catch (Exception e){
           log.info("token解析失败");
           response.setStatus(401);
           return;
       }
       log.info("token验证通过");
       filterChain.doFilter(request,response);

       //删除ThreadLocal中的数据
        CurrentHolder.remove();

    }
}
