package com.wxy.newprictice.Filter;



import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@Component
//public class JwtFilter extends HandlerInterceptorAdapter {
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    @Autowired
//    private HttpServletRequest request;
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        System.out.println("过滤方法执行了");
//        System.out.println("过滤方法执行了");
//
//        String authorization = request.getHeader("Authorization");
//        if(authorization!=null&&authorization.startsWith("Bearer ")){
//            String token = authorization.substring(7);
//            Claims claims = jwtUtil.parseJWT(token);
//            if(claims!=null){
//                if("admin".equals(claims.get("roles"))){
//                    request.setAttribute("admin_claims",claims);
//                }else{
//                    request.setAttribute("user_claims",claims);
//                }
//            }
//        }
//
//        return true; //放行
//    }
//}
