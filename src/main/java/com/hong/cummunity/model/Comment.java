package com.hong.cummunity.model;

import lombok.Data;

@Data
public class Comment {
    private Integer id;
    private Integer parentId;
    private Integer type;
    private String content;
    private Long gmtCreate;
    private Long gmtModified;
    private Long likeCount;
}
