package com.fredchen.checkin.service.impl;

import com.fredchen.checkin.dao.ClassRoomDao;
import com.fredchen.checkin.dao.StaffDao;
import com.fredchen.checkin.dao.SystemConfigDao;
import com.fredchen.checkin.dao.SystemConfigDetailDao;
import com.fredchen.checkin.domain.ClassRoom;
import com.fredchen.checkin.domain.SystemConfig;
import com.fredchen.checkin.service.IClassRoomService;
import com.fredchen.checkin.service.ISystemConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: fredchen
 * @Date: 2018/1/16 9:48
 */

@Service
public class SystemConfigService implements ISystemConfigService {

    @Autowired
    private SystemConfigDao systemConfigDao;

    @Autowired
    private SystemConfigDetailDao systemConfigDetailDao;

    @Override
    public List<SystemConfig> findByIsDel(boolean isDel) {
        return systemConfigDao.findByIsDel(isDel);
    }

    @Override
    public SystemConfig findById(Integer id) {
        return systemConfigDao.findOne(id);
    }

    @Override
    public void deleteById(Integer id) {
        systemConfigDao.deleteById(id);
        systemConfigDetailDao.deleteByConfigId(id);
    }

    @Override
    public SystemConfig save(SystemConfig systemConfig) {
        return systemConfigDao.save(systemConfig);
    }

    @Override
    public SystemConfig update(SystemConfig systemConfig) {
        return save(systemConfig);
    }
}
