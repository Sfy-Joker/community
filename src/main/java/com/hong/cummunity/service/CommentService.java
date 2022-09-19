package com.hong.cummunity.service;

import com.hong.cummunity.dto.QuestionDTO;
import com.hong.cummunity.enums.StatusMessageEnum;
import com.hong.cummunity.mapper.CommentMapper;
import com.hong.cummunity.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private QuestionService questionService;

    public void createComment(Comment comment) {

        if (comment.getType() == 0 || comment.getType() == null || comment.getType() > 2) {
            throw new RuntimeException(StatusMessageEnum.REPLY_NOT_FIND);
        }

        QuestionDTO question = questionService.findQuestionById(comment.getParentId());
        Integer dbCommentCount = commentMapper.findCommentCount(comment.getParentId());
        if (comment.getType() == 1) {
            if (question == null || comment.getParentId() == 0 || comment.getParentId() == null) {
                throw new RuntimeException(StatusMessageEnum.QUESTION_NOT_FIND);
            }
        } else {
            if (dbCommentCount == 0 || comment.getParentId() == 0 || comment.getParentId() == null) {
                throw new RuntimeException(StatusMessageEnum.REPLY_NOT_FIND);
            }
        }

        commentMapper.createComment(comment);
        questionService.addCommentCount(question);
    }
}
