package com.fredchen.checkin.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fredchen.checkin.base.BaseModel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: fredchen
 * @Date: 2018/1/15 17:04
 */

@Entity
@Table
@NoArgsConstructor
public class Staff extends BaseModel {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    private Integer sex;
    private String address;
    private String telephone;
    private String description;

    /**
     * 是否缺席
     */
    private Boolean isAbsence;
    private Date checkInTime;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "dep_id")
    private Department department;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "classroom_id")
    private ClassRoom classRoom;

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

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAbsence() {
        return isAbsence;
    }

    public void setAbsence(Boolean absence) {
        isAbsence = absence;
    }

    public Date getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(Date checkInTime) {
        this.checkInTime = checkInTime;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public ClassRoom getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(ClassRoom classRoom) {
        this.classRoom = classRoom;
    }
}
