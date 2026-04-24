package org.example.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.utils.JwtUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
//@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       /* //获取请求路径
        String path=request.getRequestURI();// /emp/list
        //判断是否登录请求，登录请求放行，剩余的请求进行token校验
        if (path.contains("login")){
            log.info("登录请求");
            return true;
        }*/
        //获取token
        String token=request.getHeader("token");
        //校验token，验证通过，放行，验证失败，返回错误信息
        if (token==null|| token.length()==0){
            log.info("token为空");
            response.setStatus(401);
            return  false;
        }
        try {
            JwtUtils.parseJWT(token);
        }catch (Exception e){
            log.info("token解析失败");
            response.setStatus(401);
            return  false;
        }
        log.info("token验证通过");
        return true;
    }
}
