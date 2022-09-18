package com.hong.cummunity.controller;

import com.hong.cummunity.dto.PaginationDTO;
import com.hong.cummunity.mapper.UserMapper;
import com.hong.cummunity.model.User;
import com.hong.cummunity.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class ProfileController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping(value = "/profile/{section}")
    public String toProfile(HttpServletRequest request,
                            Model model,
                            @PathVariable("section") String section,
                            @RequestParam(name = "currentPage", defaultValue = "1") Integer currentPage,
                            @RequestParam(name = "seize", defaultValue = "2") Integer size) {

        User user = (User) request.getSession().getAttribute("user");
        if(user == null){
            return "redirect:/";
        }

        if (section.equals("questions")) {
            model.addAttribute("sectionName", "我的文章");
        } else if (section.equals("reply")) {
            model.addAttribute("sectionName", "回复列表");
        }
        model.addAttribute("section", section);

        PaginationDTO paginationDTO = questionService.getQuestions(user.getId(), currentPage, size);
        model.addAttribute("paginationDTO", paginationDTO);

        return "profile";
    }
}
