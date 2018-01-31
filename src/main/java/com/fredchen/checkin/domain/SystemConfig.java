package com.fredchen.checkin.domain;

import com.fredchen.checkin.base.BaseModel;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

/**
 * @Author: fredchen
 * @Date: 2018/1/31 15:31
 */

@Entity
@NoArgsConstructor
@Table(name = "system_config")
public class SystemConfig extends BaseModel {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String code;
    private Integer type;
    private String value;

    @OneToMany(mappedBy = "system_config")
    private Set<SystemConfigDetail> systemConfigDetails;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Set<SystemConfigDetail> getSystemConfigDetails() {
        return systemConfigDetails;
    }

    public void setSystemConfigDetails(Set<SystemConfigDetail> systemConfigDetails) {
        this.systemConfigDetails = systemConfigDetails;
    }
}
