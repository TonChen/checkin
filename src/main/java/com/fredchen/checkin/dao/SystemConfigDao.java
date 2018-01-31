package com.fredchen.checkin.dao;

import com.fredchen.checkin.domain.SystemConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author: fredchen
 * @Date: 2018/1/16 9:45
 */
public interface SystemConfigDao extends JpaRepository<SystemConfig, Integer> {

    List<SystemConfig> findByIsDel(boolean isDel);

    @Query("update SystemConfig s set s.isDel = 1 where s.id = ?1")
    @Modifying
    void deleteById(Integer id);
}
