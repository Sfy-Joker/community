package com.hong.cummunity.enums;

public enum CommentTypeEnum {
    QUESTION_TYPE(1),
    COMMENT_TYPE(2);
    private Integer type;

    public Integer getType() {
        return type;
    }

    CommentTypeEnum(Integer type) {
        this.type = type;
    }

    public static boolean isExist(Integer type) {
        for (CommentTypeEnum value : CommentTypeEnum.values()) {
            if (value.getType() == type) {
                return true;
            }
        }
        return false;
    }
}
