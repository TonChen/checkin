package com.fredchen.checkin.service.impl;

import com.fredchen.checkin.dao.DepartmentDao;
import com.fredchen.checkin.domain.Department;
import com.fredchen.checkin.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: fredchen
 * @Date: 2018/1/16 9:48
 */

@Service
public class DepartmentService implements IDepartmentService {

    @Autowired
    private DepartmentDao departmentDao;

    @Override
    public List<Department> findByIsDel(int isDel){
        return departmentDao.findByIsDel(isDel);
    }

}
