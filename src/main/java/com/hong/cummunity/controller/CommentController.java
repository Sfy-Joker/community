package com.hong.cummunity.controller;

import com.hong.cummunity.dto.CommentDTO;
import com.hong.cummunity.exception.CustomizeErrorCode;
import com.hong.cummunity.exception.CustomizeException;
import com.hong.cummunity.model.Comment;
import com.hong.cummunity.model.User;
import com.hong.cummunity.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @ResponseBody
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Object comment(@RequestBody CommentDTO commentDTO,
                          HttpServletRequest request) {
        Comment comment = new Comment();
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setParentId(commentDTO.getParentId());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            throw new CustomizeException(CustomizeErrorCode.USER_NOT_LOGIN);
        }
        comment.setCommentator(user.getId());
        comment.setCommentator(1);
        comment.setLikeCount(0L);

        commentService.createComment(comment);
        Map<Object, Object> map = new HashMap<>();
        map.put("message", "成功");

        return map;
    }
}
