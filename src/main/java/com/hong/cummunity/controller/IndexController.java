package com.hong.cummunity.controller;

import com.hong.cummunity.dto.PaginationDTO;
import com.hong.cummunity.mapper.UserMapper;
import com.hong.cummunity.model.User;
import com.hong.cummunity.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping(value = "/")
    public String toIndex(HttpServletRequest request,
                          Model model,
                          @RequestParam(name = "currentPage", defaultValue = "1") Integer currentPage,
                          @RequestParam(name = "seize", defaultValue = "2") Integer size){
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

        PaginationDTO paginationDTO = questionService.getQuestions(currentPage, size);
        model.addAttribute("paginationDTO", paginationDTO);

        return "index";
    }

}
