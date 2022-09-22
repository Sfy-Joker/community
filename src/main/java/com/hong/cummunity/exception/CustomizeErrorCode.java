package com.hong.cummunity.exception;

import com.hong.cummunity.exception.impl.ICustomizeErrorCode;

public enum CustomizeErrorCode implements ICustomizeErrorCode {
    USER_NOT_LOGIN("用户未登录，请先登录~"),
    QUESTION_NOT_FIND("文章不存在了，请换一个试试吧！");

    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    CustomizeErrorCode(String message) {
        this.message = message;
    }
}
