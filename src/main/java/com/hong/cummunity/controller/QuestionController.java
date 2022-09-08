package com.hong.cummunity.controller;

import com.hong.cummunity.mapper.QuestionMapper;
import com.hong.cummunity.mapper.UserMapper;
import com.hong.cummunity.model.Question;
import com.hong.cummunity.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class QuestionController {
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @GetMapping(value = "/publish")
    public String toPublish(){
        return "publish";
    }

    @PostMapping(value = "/publish")
    public String submitPublish(@RequestParam(value = "title", required = false) String title,
                                @RequestParam(value = "description", required = false) String description,
                                @RequestParam(value = "tag", required = false) String tag,
                                Model model,
                                HttpServletRequest request){
        System.out.println(title);
        System.out.println(description);
        System.out.println(tag);

        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);

        if(title ==null || title.equals("")){
            model.addAttribute("error", "标题不能为空");
            return "/publish";
        }
        if(description ==null || description.equals("")){
            model.addAttribute("error", "描述不能为空");
            return "/publish";
        }
        if(tag ==null || tag.equals("")){
            model.addAttribute("error", "标签不能为空");
            return "/publish";
        }

        User user = null;
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length != 0){
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("token")){
                    String value = cookie.getValue();
                    user = userMapper.findByToken(value);
                    if(user != null){
                        request.getSession().setAttribute("user", user);

                        Question question = new Question();
                        question.setTitle(title);
                        question.setDescription(description);
                        question.setGmtCreate(System.currentTimeMillis());
                        question.setGmtModified(question.getGmtCreate());
                        question.setCreator(user.getId());
                        questionMapper.addQuestion(question);
                    }
                    break;
                }
            }
        }

        if(user == null){
            model.addAttribute("error", "用户未登录");
            return "/publish";
        }

        return "redirect:/";
    }
}
