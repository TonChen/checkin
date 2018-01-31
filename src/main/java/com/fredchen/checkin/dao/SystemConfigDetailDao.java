package com.fredchen.checkin.dao;

import com.fredchen.checkin.domain.Staff;
import com.fredchen.checkin.domain.SystemConfigDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author: fredchen
 * @Date: 2018/1/16 9:45
 */
public interface SystemConfigDetailDao extends JpaRepository<SystemConfigDetail, Integer> {

    List<SystemConfigDetail> findByIsDel(boolean isDel);

    @Query("update SystemConfigDetail s set s.isDel = 1 where s.id = ?1")
    @Modifying
    void deleteById(Integer id);

    @Query("update SystemConfigDetail s set s.isDel = 1 where s.systemConfig.id = ?1")
    @Modifying
    void deleteByConfigId(Integer id);

    @Query("select s from SystemConfigDetail s where s.systemConfig.id = ?1 and s.isDel = 0 ")
    List<SystemConfigDetail> queryByConfigId(Integer configId);
}
