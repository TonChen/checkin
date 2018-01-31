package com.fredchen.checkin.common.constant;

/**
 * @Author: fredchen
 * @Date: 2018/1/31 17:54
 */
public enum CodeEnum {

    SYSTEM_CONFIG_CODE_FUDAO("FUDAO", "辅导类型编码");



    private String code;
    private String description;

    CodeEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
