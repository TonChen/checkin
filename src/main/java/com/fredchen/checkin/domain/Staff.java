package com.fredchen.checkin.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fredchen.checkin.base.BaseModel;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: fredchen
 * @Date: 2018/1/15 17:04
 */

@Data
@Entity
@Builder
@Table
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

}
