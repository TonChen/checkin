package com.fredchen.checkin.service;

import com.fredchen.checkin.domain.SystemConfig;

import java.util.List;

/**
 * @Author: fredchen
 * @Date: 2018/1/16 10:34
 */
public interface ISystemConfigService {

    List<SystemConfig> findByIsDel(boolean isDel);

    SystemConfig findById(Integer id);

    void deleteById(Integer id);

    SystemConfig save(SystemConfig systemConfig);

    SystemConfig update(SystemConfig systemConfig);
}
