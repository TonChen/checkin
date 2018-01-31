package com.fredchen.checkin.service.impl;

import com.fredchen.checkin.dao.SystemConfigDetailDao;
import com.fredchen.checkin.domain.SystemConfigDetail;
import com.fredchen.checkin.service.ISystemConfigDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: fredchen
 * @Date: 2018/1/16 9:48
 */

@Service
public class SystemConfigDetailService implements ISystemConfigDetailService {

    @Autowired
    private SystemConfigDetailDao systemConfigDetailDao;

    @Override
    public List<SystemConfigDetail> findByIsDel(boolean isDel) {
        return systemConfigDetailDao.findByIsDel(isDel);
    }

    @Override
    public SystemConfigDetail findById(Integer id) {
        return systemConfigDetailDao.findOne(id);
    }

    @Override
    public List<SystemConfigDetail> queryByConfigId(Integer configId) {
        return systemConfigDetailDao.queryByConfigId(configId);
    }

    @Override
    public void deleteById(Integer id) {
        SystemConfigDetail systemConfigDetail = findById(id);
        systemConfigDetail.setIsDel(true);
        systemConfigDetailDao.save(systemConfigDetail);
    }

    @Override
    public SystemConfigDetail save(SystemConfigDetail systemConfigDetail) {
        return systemConfigDetailDao.save(systemConfigDetail);
    }

    @Override
    public SystemConfigDetail update(SystemConfigDetail systemConfigDetail) {
        return save(systemConfigDetail);
    }
}
