package com.hong.cummunity.controller;

import com.hong.cummunity.dto.QuestionDTO;
import com.hong.cummunity.mapper.QuestionMapper;
import com.hong.cummunity.model.Question;
import com.hong.cummunity.model.User;
import com.hong.cummunity.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class PublishController {
    @Autowired
    private QuestionService questionService;

    @GetMapping(value = "/publish")
    public String toPublish() {
        return "publish";
    }

    @PostMapping(value = "/publish")
    public String submitPublish(@RequestParam(value = "title", required = false) String title,
                                @RequestParam(value = "description", required = false) String description,
                                @RequestParam(value = "tag", required = false) String tag,
                                @RequestParam(value = "id", required = false) Integer id,
                                Model model,
                                HttpServletRequest request) {

        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);

        if (title == null || title.equals("")) {
            model.addAttribute("error", "标题不能为空");
            return "/publish";
        }
        if (description == null || description.equals("")) {
            model.addAttribute("error", "附加内容不能为空");
            return "/publish";
        }
        if (tag == null || tag.equals("")) {
            model.addAttribute("error", "标签不能为空");
            return "/publish";
        }

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user != null) {
            Question question = new Question();
            question.setTitle(title);
            question.setDescription(description);
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            question.setCreator(user.getId());
            question.setTag(tag);
            question.setId(id);
            questionService.createOrUpdate(question);
        } else {
            model.addAttribute("error", "用户未登录");
            return "/publish";
        }

        return "redirect:/";
    }

    @GetMapping(value = "/publish/{id}")
    public String edit(@PathVariable("id") Integer id,
                       Model model) {
        QuestionDTO questionDTO = questionService.findQuestionById(id);
        model.addAttribute("title", questionDTO.getTitle());
        model.addAttribute("description", questionDTO.getDescription());
        model.addAttribute("tag", questionDTO.getTag());
        model.addAttribute("id", questionDTO.getId());
        return "edit";
    }
}
