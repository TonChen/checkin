package com.fredchen.checkin.dao;

import com.fredchen.checkin.domain.LeaveInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author: fredchen
 * @Date: 2018/1/16 9:45
 */
public interface LeaveInfoDao extends JpaRepository<LeaveInfo, Integer> {

    List<LeaveInfo> findByIsDelOrderByCreateTimeDesc(boolean isDel);

    @Query("update LeaveInfo o set o.isDel = 1 where o.id = ?1")
    @Modifying
    void deleteById(Integer id);

    LeaveInfo findByType(Integer type);

    @Query(value = "select * from leave_info o where o.is_del = 0 and o.staff_id = ?1 order by o.create_time DESC ", nativeQuery = true)
    List<LeaveInfo> findByStaffId(Integer staffId);
}
