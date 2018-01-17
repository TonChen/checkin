package com.fredchen.checkin.dao;

import com.fredchen.checkin.domain.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: fredchen
 * @Date: 2018/1/16 9:45
 */

public interface StaffDao extends JpaRepository<Staff, Integer> {


    @Query("select s from Staff s where s.department.id = ?1 and s.isDel = 0 ")
    List<Staff> queryDepartmentId(Integer depId);


}
