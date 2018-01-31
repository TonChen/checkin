package com.fredchen.checkin.domain;

import com.fredchen.checkin.base.BaseModel;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

/**
 * @Author: fredchen
 * @Date: 2018/1/31 15:15
 */

@Entity
@NoArgsConstructor
@Table(name = "class_room")
public class ClassRoom extends BaseModel {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String code;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToMany(mappedBy = "class_room")
    private Set<Staff> staffs;

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

    public Set<Staff> getStaffs() {
        return staffs;
    }

    public void setStaffs(Set<Staff> staffs) {
        this.staffs = staffs;
    }
}
