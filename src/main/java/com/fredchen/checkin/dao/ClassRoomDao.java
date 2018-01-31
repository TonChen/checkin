package com.fredchen.checkin.dao;

import com.fredchen.checkin.domain.ClassRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author: fredchen
 * @Date: 2018/1/16 9:45
 */
public interface ClassRoomDao extends JpaRepository<ClassRoom, Integer> {

    List<ClassRoom> findByIsDel(boolean isDel);

    @Query("update ClassRoom c set c.isDel = 1 where c.id = ?1")
    @Modifying
    void deleteById(Integer id);

    ClassRoom findByName(String depName);
}
