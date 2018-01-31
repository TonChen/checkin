package com.fredchen.checkin.common.constant;

/**
 * @Author: fredchen
 * @Date: 2018/1/15 16:55
 */
public enum MessageEnum {

    BUSINESS_EXCEPTION("1000", "业务异常"),
    PARAMETER_EXCEPTION("1001", "参数错误")
    ;

    MessageEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private String code;
    private String message;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
