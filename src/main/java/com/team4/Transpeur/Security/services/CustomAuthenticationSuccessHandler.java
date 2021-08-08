package com.team4.Transpeur.Security.services;

import com.team4.Transpeur.Security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
 
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private JwtUtils jwtUtils;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        UserDetailsImpl authUser = (UserDetailsImpl) authentication.getPrincipal();
        String jwt = jwtUtils.generateJwtToken(authentication);
        Cookie cookie = new Cookie("accessToken", jwt);
        cookie.setPath("/");
        cookie.setSecure(true);
        cookie.setMaxAge(1*24*60*60);
        cookie.setHttpOnly(true);

        Cookie cookie2 = new Cookie("uname", authUser.getUsername());
        cookie.setPath("/");
        cookie.setSecure(false);
        cookie.setMaxAge(1*24*60*60);
        cookie.setHttpOnly(false);

        Cookie cookie3 = new Cookie("uid", authUser.getId()+"");
        cookie.setPath("/");
        cookie.setSecure(false);
        cookie.setMaxAge(1*24*60*60);
        cookie.setHttpOnly(false);

        httpServletResponse.addCookie(cookie);
        httpServletResponse.addCookie(cookie2);
        httpServletResponse.addCookie(cookie3);
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
 
        httpServletResponse.sendRedirect("/actives");
    }
}