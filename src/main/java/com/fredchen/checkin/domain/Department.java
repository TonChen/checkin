package com.fredchen.checkin.domain;

import com.fredchen.checkin.base.BaseModel;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

/**
 * @Author: fredchen
 * @Date: 2018/1/15 17:39
 */

@Data
@Builder
@Entity
@Table(name = "department")
public class Department extends BaseModel {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "department")
    private Set<Staff> staffs;


}
