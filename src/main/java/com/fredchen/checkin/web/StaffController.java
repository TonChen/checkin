package com.fredchen.checkin.web;

import com.fredchen.checkin.base.BaseController;
import com.fredchen.checkin.domain.Staff;
import com.fredchen.checkin.service.IClassRoomService;
import com.fredchen.checkin.service.IDepartmentService;
import com.fredchen.checkin.service.IStaffService;
import lombok.NonNull;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @Author: fredchen
 * @Date: 2018/1/15 17:58
 */

@Controller
@RequestMapping("/staff")
public class StaffController extends BaseController {

    @Autowired
    private IStaffService staffService;
    @Autowired
    private IDepartmentService departmentService;
    @Autowired
    private IClassRoomService classRoomService;

    @RequestMapping("/list")
    public Object list(@RequestParam(required = false) Boolean isAbsence, @RequestParam(name = "depId", required = false) Integer depId, @RequestParam(required = false) Integer roomId, Model model){
        val deps = departmentService.findByIsDel(false);
        val rooms = classRoomService.findByIsDel(false);
        List<Staff> staffs = staffService.withDepartmentId(depId, roomId);
        if(isAbsence != null){
            staffs = staffs.stream().filter(n -> n.getAbsence().equals(isAbsence)).collect(Collectors.toList());
        }
        model.addAttribute("deps", deps);
        model.addAttribute("staffs", staffs);
        model.addAttribute("rooms", rooms);
        model.addAttribute("depId", depId);
        model.addAttribute("roomId", roomId);
        model.addAttribute("isAbsence", isAbsence);
        return "staff/list";
    }

    @RequestMapping("/show")
    public String show(@RequestParam(required = false) Boolean isAbsence, @RequestParam(name = "depId", required = false) Integer depId, @RequestParam(required = false) Integer roomId, Model model) {
        val deps = departmentService.findByIsDel(false);
        val rooms = classRoomService.findByIsDel(false);
        List<Staff> staffs = staffService.withDepartmentId(depId, roomId);
        if(isAbsence != null){
            staffs = staffs.stream().filter(n -> n.getAbsence().equals(isAbsence)).collect(Collectors.toList());
        }
        model.addAttribute("deps", deps);
        model.addAttribute("staffs", staffs);
        model.addAttribute("depId", depId);
        model.addAttribute("rooms", rooms);
        model.addAttribute("roomId", roomId);
        model.addAttribute("isAbsence", isAbsence);
        return "display/list";
    }

    @GetMapping("/create")
    public String create(Model model) {
        val deps = departmentService.findByIsDel(false);
        val rooms = classRoomService.findByIsDel(false);
        model.addAttribute("deps", deps);
        model.addAttribute("rooms", rooms);
        return "staff/create";
    }

    @GetMapping("/update")
    public String update(@RequestParam(name = "id") Integer id, Model model) {
        val staff = staffService.findById(id);
        val deps = departmentService.findByIsDel(false);
        val rooms = classRoomService.findByIsDel(false);
        model.addAttribute("deps", deps);
        model.addAttribute("rooms", rooms);
        model.addAttribute("staff", staff);
        return "staff/update";
    }

    @PostMapping("/saveOrUpdate")
    public String saveOrUpdate(Staff staff ,@RequestParam("departmentId") Integer departmentId, @RequestParam("classroomId") Integer classroomId) {
        val dep = departmentService.findById(departmentId);
        val room = classRoomService.findById(classroomId);
        if(staff.getId() == null){
            staff.setIsDel(false);
            staff.setCreateTime(new Date());
            staff.setAbsence(false);
            staff.setCheckInTime(new Date());
            staff.setDepartment(dep);
            staff.setClassRoom(room);
            staffService.save(staff);
        }else{
            val sta = staffService.findById(staff.getId());
            sta.setClassRoom(room);
            sta.setDepartment(dep);
            sta.setDescription(staff.getDescription());
            sta.setName(staff.getName());
            sta.setAddress(staff.getAddress());
            sta.setSex(staff.getSex());
            sta.setTelephone(staff.getTelephone());
            staffService.update(sta);
        }
        return "redirect:list?depId=";
    }

    @GetMapping("/delete")
    @Transactional
    public String delete(@RequestParam("id") Integer id) {
        staffService.deleteById(id);
        return "redirect:list";
    }

    /**
     * 签到
     * @param id
     * @return
     */
    @RequestMapping("/checkIn")
    @ResponseBody
    @Transactional
    public String checkIn(@RequestParam @NonNull Integer id, @RequestParam(name = "isAbsence") String isAbsence) {
        val staff = staffService.findById(id);
        staff.setAbsence("是".equals(isAbsence)?false:true);
        staff.setCheckInTime(new Date());
        Staff sta = staffService.update(staff);
        return sta.getAbsence()?"是":"否";
    }

}
