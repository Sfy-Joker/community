package com.hong.cummunity.dto;

import com.hong.cummunity.model.User;
import lombok.Data;

@Data
public class QuestionDTO {
    private String title;
    private String description;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private User user;

}
