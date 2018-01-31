package com.fredchen.checkin.web;

import com.fredchen.checkin.base.BaseController;
import com.fredchen.checkin.domain.ClassRoom;
import com.fredchen.checkin.service.IClassRoomService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

/**
 * @Author: fredchen
 * @Date: 2018/1/16 13:42
 */

@Controller
@RequestMapping("/classRoom")
public class ClassRoomController extends BaseController {

    @Autowired
    private IClassRoomService classRoomServicce;

    @GetMapping("/list")
    public String list(Model model) {
        val rooms = classRoomServicce.findByIsDel(false);
        model.addAttribute("rooms", rooms);
        return "classRoom/list";
    }


    @GetMapping("/create")
    public String create(Model model) {
        return "classRoom/create";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") Integer id, Model model) {
        val room = classRoomServicce.findById(id);
        model.addAttribute("room", room);
        return "classRoom/update";
    }

    @PostMapping("/saveOrUpdate")
    public String saveOrUpdate(ClassRoom classRoom) {
        if(classRoom.getId() == null){
            classRoom.setIsDel(false);
            classRoom.setCreateTime(new Date());
            classRoomServicce.save(classRoom);
        }else{
            val room = classRoomServicce.findById(classRoom.getId());
            room.setDescription(classRoom.getDescription());
            room.setName(classRoom.getName());
            room.setCode(classRoom.getCode());
            classRoomServicce.update(room);
        }
        return "redirect:list";
    }

    @GetMapping("/delete")
    @Transactional
    public String delete(@RequestParam("id") Integer id) {
        classRoomServicce.deleteById(id);
        return "redirect:list";
    }


}
