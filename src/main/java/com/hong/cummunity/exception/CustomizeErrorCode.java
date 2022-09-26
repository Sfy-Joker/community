package com.hong.cummunity.exception;

import com.hong.cummunity.exception.impl.ICustomizeErrorCode;

public enum CustomizeErrorCode implements ICustomizeErrorCode {
    USER_NOT_LOGIN(2001, "当前操作需要登录，请登录后重试。"),
    QUESTION_NOT_FIND(2002, "文章不存在了，请换一个试试吧！"),
    REPLY_NOT_FIND(2003, "回复不存在了，请换一个试试吧！"),
    TYPE_PARAM_WRONG(2004, "评论类型错误或不存在。"),
    SERVER_ERROR(2005, "服务器冒烟了，要不然你稍后再试试！！"),
    TARGET_PARENT_NOT_FIND(2005, "未选中任何问题或回复进行回复。");


    private Integer code;
    private String message;

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    CustomizeErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
