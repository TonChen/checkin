package com.fredchen.checkin.web;

import com.fredchen.checkin.base.BaseController;
import com.fredchen.checkin.domain.Staff;
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

    @GetMapping("/list")
    public Object list(@RequestParam(name = "depId", defaultValue = "1") Integer depId, Model model){
        val deps = departmentService.findByIsDel(false);
        val staffs = staffService.withDepartmentId(depId);
        model.addAttribute("deps", deps);
        model.addAttribute("staffs", staffs);
        model.addAttribute("depId", depId);

        return "staff/list";
    }

    @RequestMapping("/show")
    public String show(@RequestParam(name = "depId", required = false) Integer depId, Model model) {
        val deps = departmentService.findByIsDel(false);
        val staffs = staffService.withDepartmentId(depId);
        model.addAttribute("deps", deps);
        model.addAttribute("staffs", staffs);
        model.addAttribute("depId", depId);
        return "display/list";
    }

    @GetMapping("/create")
    public String create(Model model, @RequestParam("depId") @NonNull Integer depId) {
        val deps = departmentService.findByIsDel(false);
        model.addAttribute("deps", deps);
        model.addAttribute("depId", depId);
        return "staff/create";
    }

    @GetMapping("/update")
    public String update(@RequestParam(name = "id", defaultValue = "1") Integer id, Model model, @RequestParam("depId") Integer depId) {
        val staff = staffService.findById(id);
        val deps = departmentService.findByIsDel(false);
        model.addAttribute("deps", deps);
        model.addAttribute("staff", staff);
        model.addAttribute("depId", depId);
        return "staff/update";
    }

    @PostMapping("/saveOrUpdate")
    public String saveOrUpdate(Staff staff, @RequestParam("depId") Integer depId, @RequestParam("departmentId") Integer departmentId) {
        val dep = departmentService.findById(departmentId);
        if(staff.getId() == null){
            staff.setIsDel(false);
            staff.setCreateTime(new Date());
            staff.setAbsence(false);
            staff.setCheckInTime(new Date());
            staff.setDepartment(dep);
            staffService.save(staff);
        }else{
            val sta = staffService.findById(staff.getId());
            sta.setDepartment(dep);
            sta.setDescription(staff.getDescription());
            sta.setName(staff.getName());
            sta.setAddress(staff.getAddress());
            sta.setSex(staff.getSex());
            sta.setTelephone(staff.getTelephone());
            staffService.update(sta);
        }
        return "redirect:list?depId="+depId;
    }

    @GetMapping("/delete")
    @Transactional
    public String delete(@RequestParam("id") Integer id, Model model, @RequestParam("depId") Integer depId) {
        staffService.deleteById(id);
        return "redirect:list?depId="+depId;
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
        Staff sta = staffService.update(staff);
        return sta.getAbsence()?"是":"否";
    }

}
