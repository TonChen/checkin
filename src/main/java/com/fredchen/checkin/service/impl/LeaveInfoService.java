package com.fredchen.checkin.service.impl;

import com.fredchen.checkin.dao.LeaveInfoDao;
import com.fredchen.checkin.domain.LeaveInfo;
import com.fredchen.checkin.service.ILeaveInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: fredchen
 * @Date: 2018/1/16 9:48
 */

@Service
public class LeaveInfoService implements ILeaveInfoService {

    @Autowired
    private LeaveInfoDao leaveInfoDao;

    @Override
    public List<LeaveInfo> findByIsDel(boolean isDel) {
        return leaveInfoDao.findByIsDelOrderByCreateTimeDesc(isDel);
    }

    @Override
    public List<LeaveInfo> findByStaffId(Integer staffId) {
        if(staffId == null){
            return findByIsDel(false);
        }
        return leaveInfoDao.findByStaffId(staffId);
    }

    @Override
    public LeaveInfo findById(Integer id) {
        return leaveInfoDao.findOne(id);
    }

    @Override
    public void deleteById(Integer id) {
        leaveInfoDao.deleteById(id);
    }

    @Override
    public LeaveInfo save(LeaveInfo leaveInfo) {
        return leaveInfoDao.save(leaveInfo);
    }

    @Override
    public LeaveInfo update(LeaveInfo leaveInfo) {
        return save(leaveInfo);
    }

    @Override
    public LeaveInfo findByType(Integer type) {
        return leaveInfoDao.findByType(type);
    }
}
