package com.hong.cummunity.controller;

import com.hong.cummunity.dto.QuestionDTO;
import com.hong.cummunity.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;
            
    @GetMapping(value = "/question/{id}")
    public String toQuestion(@PathVariable("id") Integer id,
                             Model model){
        QuestionDTO questionDTO = questionService.findQuestionById(id);

        if(questionDTO != null){
            int i = questionService.updateViewCount(id);
        }

        model.addAttribute("questionDTO", questionDTO);
        return "question";
    }


}



