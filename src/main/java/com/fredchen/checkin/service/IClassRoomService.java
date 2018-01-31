package com.fredchen.checkin.service;

import com.fredchen.checkin.domain.ClassRoom;

import java.util.List;

/**
 * @Author: fredchen
 * @Date: 2018/1/16 10:34
 */
public interface IClassRoomService {

    List<ClassRoom> findByIsDel(boolean isDel);

    ClassRoom findById(Integer id);

    void deleteById(Integer id);

    ClassRoom save(ClassRoom classRoom);

    ClassRoom update(ClassRoom classRoom);

    ClassRoom findByName(String depName);
}
