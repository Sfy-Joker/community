package com.hong.cummunity.controller;

import com.hong.cummunity.mapper.UserMapper;
import com.hong.cummunity.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping(value = "/")
    public String toIndex(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(request.getCookies() != null && request.getCookies().length != 0){
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("token")){
                    String value = cookie.getValue();
                    User user = userMapper.findByToken(value);
                    if(user != null){
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        return "index";
    }

}
