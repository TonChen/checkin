package com.fredchen.checkin.service;

import com.fredchen.checkin.domain.LeaveInfo;

import java.util.List;

/**
 * @Author: fredchen
 * @Date: 2018/1/16 10:34
 */
public interface ILeaveInfoService {

    List<LeaveInfo> findByIsDel(boolean isDel);

    List<LeaveInfo> findByStaffId(Integer staffId);

    LeaveInfo findById(Integer id);

    void deleteById(Integer id);

    LeaveInfo save(LeaveInfo leaveInfo);

    LeaveInfo update(LeaveInfo leaveInfo);

    LeaveInfo findByType(Integer type);
}
