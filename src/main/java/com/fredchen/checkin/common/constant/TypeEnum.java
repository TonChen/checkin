package com.fredchen.checkin.common.constant;

/**
 * @Author: fredchen
 * @Date: 2018/1/31 17:53
 */
public enum TypeEnum {

    SYSTEM_CONFIG_TYPE_FUDAO(1000, "辅导类型");



    private Integer type;
    private String description;

    TypeEnum(Integer type, String description) {
        this.type = type;
        this.description = description;
    }

    public Integer getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }
}
