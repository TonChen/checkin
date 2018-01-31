package com.fredchen.checkin.dao;

import com.fredchen.checkin.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author: fredchen
 * @Date: 2018/1/16 9:45
 */
public interface DepartmentDao extends JpaRepository<Department, Integer> {

    List<Department> findByIsDel(boolean isDel);

    @Query("update Department d set d.isDel = 1 where d.id = ?1")
    @Modifying
    void deleteById(Integer id);

    Department findByName(String depName);
}
