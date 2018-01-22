package com.fredchen.checkin.service.impl;

import com.fredchen.checkin.dao.StaffDao;
import com.fredchen.checkin.domain.Staff;
import com.fredchen.checkin.service.IStaffService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: fredchen
 * @Date: 2018/1/16 9:48
 */

@Service
public class StaffService implements IStaffService {

    @Autowired
    private StaffDao staffDao;


    @Override
    public List<Staff> withDepartmentId(Integer depId){
        return staffDao.queryDepartmentId(depId);
    }

    @Override
    public Staff findById(Integer id) {
        return staffDao.findOne(id);
    }

    @Override
    public Staff save(Staff staff) {
        return staffDao.save(staff);
    }

    @Override
    public Staff update(Staff staff) {
        return save(staff);
    }

    @Override
    public void deleteById(Integer id) {
        staffDao.deleteById(id);
    }

}
