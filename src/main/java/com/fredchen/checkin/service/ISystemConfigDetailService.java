package com.fredchen.checkin.service;

import com.fredchen.checkin.domain.SystemConfigDetail;

import java.util.List;

/**
 * @Author: fredchen
 * @Date: 2018/1/16 10:34
 */
public interface ISystemConfigDetailService {

    List<SystemConfigDetail> findByIsDel(boolean isDel);

    SystemConfigDetail findById(Integer id);

    List<SystemConfigDetail> queryByConfigId(Integer configId);

    void deleteById(Integer id);

    SystemConfigDetail save(SystemConfigDetail systemConfigDetail);

    SystemConfigDetail update(SystemConfigDetail systemConfigDetail);
}
