package com.retroswap.RetroSwap_Backend.Config;


import com.retroswap.RetroSwap_Backend.Service.Security.JWT_service;
import com.retroswap.RetroSwap_Backend.Service.Security.MyuserdetailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class JWTFilter extends OncePerRequestFilter {
    @Autowired
    JWT_service jwtService;
    @Autowired
    ApplicationContext context;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authheader=request.getHeader("Authorization");
        String token=null;
        String username=null;
        System.out.println(authheader);

        if(authheader!=null && authheader.startsWith("Bearer ")){
            token=authheader.substring(7);
            System.out.println(token);
            username=jwtService.extractUserName(token);

        }
        if(username!=null&& SecurityContextHolder.getContext().getAuthentication()==null)
        {
            UserDetails userDetails=  context.getBean(MyuserdetailService.class).loadUserByUsername(username);
            if(jwtService.validateToken(token,userDetails))
            {
                UsernamePasswordAuthenticationToken authtoken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authtoken);
            }

        }
        filterChain.doFilter(request,response);
    }
}
