package com.fredchen.checkin.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fredchen.checkin.base.BaseModel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @Author: fredchen
 * @Date: 2018/2/1 13:53
 */

@Entity
@NoArgsConstructor
@Table
@EqualsAndHashCode(callSuper = true)
public class LeaveInfo extends BaseModel {

    @Id
    @GeneratedValue
    private Integer id;
    private String title;
    private String content;
    private Integer type;
    private String remark;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "staff_id")
    private Staff staff;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }
}
