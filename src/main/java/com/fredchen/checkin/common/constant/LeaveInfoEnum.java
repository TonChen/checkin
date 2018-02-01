package com.fredchen.checkin.common.constant;

/**
 * @Author: fredchen
 * @Date: 2018/2/1 14:08
 */
public enum LeaveInfoEnum {

    LEAVE_INFO_TYPE_CHUCHAI(2000, "出差"),
    LEAVE_INFO_TYPE_TIAOXIU(2001, "调休"),
    LEAVE_INFO_TYPE_FADING(2002, "国家法定假期"),
    LEAVE_INFO_TYPE_HUNJIA(2003, "婚假"),
    LEAVE_INFO_TYPE_NIANJIA(2004, "年假"),
    LEAVE_INFO_TYPE_SANGJIA(2005, "丧假"),
    LEAVE_INFO_TYPE_XIUJIA(2006, "休假"),
    LEAVE_INFO_TYPE_SHIJIA(2007, "事假"),
    LEAVE_INFO_TYPE_CHANJIA(2008, "产假"),
    LEAVE_INFO_TYPE_KUANGJIA(2009, "旷假"),
    LEAVE_INFO_TYPE_PEICHANJIA(2010, "陪产假"),
    LEAVE_INFO_TYPE_BINGJIA(2011, "病假"),
    LEAVE_INFO_TYPE_OTHER(2012, "其他"),
    ;

    private Integer type;
    private String description;

    LeaveInfoEnum(Integer type, String description) {
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
