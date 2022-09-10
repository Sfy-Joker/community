package com.hong.cummunity.service;

import com.hong.cummunity.dto.QuestionDTO;
import com.hong.cummunity.mapper.QuestionMapper;
import com.hong.cummunity.mapper.UserMapper;
import com.hong.cummunity.model.Question;
import com.hong.cummunity.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;
    public List<QuestionDTO> getQuestions(){
        List<Question> questions = questionMapper.getQuestions();
        List<QuestionDTO> list = new ArrayList<>();
        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            list.add(questionDTO);
        }

        return list;
    }
}
