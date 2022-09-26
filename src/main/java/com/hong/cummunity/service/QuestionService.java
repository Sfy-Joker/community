package com.hong.cummunity.service;

import com.hong.cummunity.dto.PaginationDTO;
import com.hong.cummunity.dto.QuestionDTO;
import com.hong.cummunity.exception.CustomizeErrorCode;
import com.hong.cummunity.exception.CustomizeException;
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

    public PaginationDTO getQuestions(Integer currentPage, Integer size) {
        Integer offset = size * (currentPage - 1);
        List<Question> questions = questionMapper.getQuestions(offset, size);
        List<QuestionDTO> list = new ArrayList<>();
        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            list.add(questionDTO);
        }

        PaginationDTO paginationDTO = new PaginationDTO();
        paginationDTO.setQuestions(list);
        Integer totalCount = questionMapper.count();
        paginationDTO.handle(currentPage, size, totalCount);

        return paginationDTO;
    }

    public PaginationDTO getQuestions(Integer userId, Integer currentPage, Integer size) {
        Integer offset = size * (currentPage - 1);
        List<Question> questions = questionMapper.getQuestionsById(userId, offset, size);
        List<QuestionDTO> list = new ArrayList<>();
        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            list.add(questionDTO);
        }

        PaginationDTO paginationDTO = new PaginationDTO();
        paginationDTO.setQuestions(list);
        Integer totalCount = questionMapper.countById(userId);
        paginationDTO.handle(currentPage, size, totalCount);

        return paginationDTO;
    }

    public QuestionDTO findQuestionById(Integer id) {
        Question question = questionMapper.findQuestionById(id);
        if (question == null) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FIND);
        }
        User user = userMapper.findById(question.getCreator());
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        Question dbQuestion = questionMapper.findQuestionById(question.getId());
        if (dbQuestion == null) {
            if (question.getViewCount() == null) {
                question.setViewCount(0);
            }
            if (question.getCommentCount() == null) {
                question.setCommentCount(0);
            }
            if (question.getLikeCount() == null) {
                question.setLikeCount(0);
            }
            questionMapper.addQuestion(question);
        } else {
            question.setGmtModified(System.currentTimeMillis());
            questionMapper.update(question);
        }
    }

    public void updateViewCount(Integer id) {
        Question question = questionMapper.findQuestionById(id);
        if (question.getViewCount() == null) {
            question.setViewCount(1);
        }
        questionMapper.updateViewCount(question);
    }

    public void addCommentCount(QuestionDTO question) {
        questionMapper.addCommentCount(question);
    }
}
