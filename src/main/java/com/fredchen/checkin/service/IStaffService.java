package com.fredchen.checkin.service;

import com.fredchen.checkin.domain.Staff;

import java.util.List;

/**
 * @Author: fredchen
 * @Date: 2018/1/16 10:31
 */
public interface IStaffService {

    List<Staff> withDepartmentId(Integer depId, Integer roomId);

    Staff findById(Integer id);

    Staff save(Staff staff);

    Staff update(Staff sta);

    void deleteById(Integer id);
}
