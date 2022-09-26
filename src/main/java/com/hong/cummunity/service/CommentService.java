package com.hong.cummunity.service;

import com.hong.cummunity.dto.QuestionDTO;
import com.hong.cummunity.enums.CommentTypeEnum;
import com.hong.cummunity.exception.CustomizeErrorCode;
import com.hong.cummunity.exception.CustomizeException;
import com.hong.cummunity.mapper.CommentMapper;
import com.hong.cummunity.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private QuestionService questionService;

    @Transactional
    public void createComment(Comment comment) {

        if (comment.getParentId() == 0 || comment.getParentId() == null) {
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARENT_NOT_FIND);
        }

        if (comment.getType() == 0 || !CommentTypeEnum.isExist(comment.getType())) {
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }

        QuestionDTO question = questionService.findQuestionById(comment.getParentId());
        Integer dbCommentCount = commentMapper.findCommentCount(comment.getParentId());
        if (comment.getType() == CommentTypeEnum.QUESTION_TYPE.getType()) {
            //回复文章
            if (question == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FIND);
            }
        } else {
            //回复评论
            if (dbCommentCount == 0) {
                throw new CustomizeException(CustomizeErrorCode.REPLY_NOT_FIND);
            }
        }
        commentMapper.createComment(comment);
        questionService.addCommentCount(question);
    }
}
