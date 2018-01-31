package com.fredchen.checkin.service.impl;

import com.fredchen.checkin.dao.ClassRoomDao;
import com.fredchen.checkin.dao.DepartmentDao;
import com.fredchen.checkin.dao.StaffDao;
import com.fredchen.checkin.domain.ClassRoom;
import com.fredchen.checkin.domain.Department;
import com.fredchen.checkin.service.IClassRoomService;
import com.fredchen.checkin.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: fredchen
 * @Date: 2018/1/16 9:48
 */

@Service
public class ClassRoomService implements IClassRoomService {

    @Autowired
    private ClassRoomDao classRoomDao;
    @Autowired
    private StaffDao staffDao;

    @Override
    public List<ClassRoom> findByIsDel(boolean isDel) {
        return classRoomDao.findByIsDel(isDel);
    }

    @Override
    public ClassRoom findById(Integer id) {
        return classRoomDao.findOne(id);
    }

    @Override
    public void deleteById(Integer id) {
        classRoomDao.deleteById(id);
        staffDao.deleteByDepId(id);
    }

    @Override
    public ClassRoom save(ClassRoom classRoom) {
        return classRoomDao.save(classRoom);
    }

    @Override
    public ClassRoom update(ClassRoom classRoom) {
        return save(classRoom);
    }

    @Override
    public ClassRoom findByName(String depName) {
        return classRoomDao.findByName(depName);
    }
}
