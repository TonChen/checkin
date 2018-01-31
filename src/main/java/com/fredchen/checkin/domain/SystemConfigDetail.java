package com.fredchen.checkin.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fredchen.checkin.base.BaseModel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @Author: fredchen
 * @Date: 2018/1/31 15:31
 */

@NoArgsConstructor
@Table(name = "system_config_detail")
@Entity
public class SystemConfigDetail extends BaseModel {

    @Id
    @GeneratedValue
    private Integer id;
    private Integer type;
    private String name;
    private String code;
    private String value;
    private String remark;
    private String extra;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "system_config_id")
    private SystemConfig systemConfig;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public SystemConfig getSystemConfig() {
        return systemConfig;
    }

    public void setSystemConfig(SystemConfig systemConfig) {
        this.systemConfig = systemConfig;
    }
}
