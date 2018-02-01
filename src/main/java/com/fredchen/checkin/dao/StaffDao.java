package com.fredchen.checkin.dao;

import com.fredchen.checkin.domain.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author: fredchen
 * @Date: 2018/1/16 9:45
 */

public interface StaffDao extends JpaRepository<Staff, Integer> {


    @Query("select s from Staff s where s.department.id = ?1 and s.isDel = 0 ")
    List<Staff> queryDepartmentId(Integer depId);


    @Query("update Staff s set s.isDel = 1 where s.department.id = ?1 ")
    @Modifying
    void deleteByDepId(Integer id);

    @Query("update Staff s set s.isDel = 1 where s.id = ?1")
    @Modifying
    void deleteById(Integer id);

    List<Staff> findByIsDel(boolean isDel);

    @Query("select s from Staff s where s.isDel = 0 and s.classRoom.id = ?1 ")
    List<Staff> queryByRoomId(Integer roomId);

    @Query("select s from Staff s where s.isDel = 0 and s.classRoom.id = ?1 and  s.department.id = ?2 ")
    List<Staff> findByRoomIdAndDepId(Integer roomId, Integer depId);
}
