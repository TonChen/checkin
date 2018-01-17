package com.fredchen.checkin.dao;

import com.fredchen.checkin.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: fredchen
 * @Date: 2018/1/16 9:45
 */
public interface DepartmentDao extends JpaRepository<Department, Integer> {

    List<Department> findByIsDel(int isDel);
}
