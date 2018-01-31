package com.fredchen.checkin.service.impl;

import com.fredchen.checkin.dao.DepartmentDao;
import com.fredchen.checkin.dao.StaffDao;
import com.fredchen.checkin.domain.Department;
import com.fredchen.checkin.service.IDepartmentService;
import lombok.val;
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
    @Autowired
    private StaffDao staffDao;

    @Override
    public List<Department> findByIsDel(boolean isDel) {
        return departmentDao.findByIsDel(isDel);
    }

    @Override
    public Department findById(Integer id) {
        return departmentDao.findOne(id);
    }

    @Override
    public void deleteById(Integer id) {
        departmentDao.deleteById(id);
        staffDao.deleteByDepId(id);
    }

    @Override
    public Department save(Department department) {
        return departmentDao.save(department);
    }

    @Override
    public Department update(Department department) {
        return save(department);
    }

    @Override
    public Department findByName(String depName) {
        return departmentDao.findByName(depName);
    }
}
