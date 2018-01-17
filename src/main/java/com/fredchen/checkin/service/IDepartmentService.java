package com.fredchen.checkin.service;

import com.fredchen.checkin.domain.Department;

import java.util.List;

/**
 * @Author: fredchen
 * @Date: 2018/1/16 10:34
 */
public interface IDepartmentService {

    List<Department> findByIsDel(int isDel);
}
