package com.fredchen.checkin.web;

import com.fredchen.checkin.base.BaseController;
import com.fredchen.checkin.common.constant.LeaveInfoEnum;
import com.fredchen.checkin.domain.LeaveInfo;
import com.fredchen.checkin.service.ILeaveInfoService;
import com.fredchen.checkin.service.IStaffService;
import lombok.NonNull;
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
import java.util.HashMap;

/**
 * @Author: fredchen
 * @Date: 2018/1/16 13:42
 */

@Controller
@RequestMapping("/leaveInfo")
public class LeaveInfoController extends BaseController {

    @Autowired
    private ILeaveInfoService leaveInfoService;
    @Autowired
    private IStaffService staffService;

    @GetMapping("/list")
    public String list(Model model, @RequestParam Integer staffId) {
        val infos = leaveInfoService.findByStaffId(staffId);
        model.addAttribute("infos", infos);

        LeaveInfoEnum[] values = LeaveInfoEnum.values();
        val map = new HashMap<Integer, String>();
        for (LeaveInfoEnum value: values) {
            map.put(value.getType(), value.getDescription());
        }
        model.addAttribute("map", map);

        return "leaveInfo/list";
    }

    @GetMapping("/create")
    public String create(Model model, @RequestParam @NonNull Integer staffId) {
        LeaveInfoEnum[] values = LeaveInfoEnum.values();
        val map = new HashMap<Integer, String>();
        for (LeaveInfoEnum value: values) {
            map.put(value.getType(), value.getDescription());
        }
        model.addAttribute("map", map);
        model.addAttribute("staffId", staffId);
        return "display/create";
    }

    @PostMapping("/saveOrUpdate")
    public String saveOrUpdate(LeaveInfo leaveInfo, @RequestParam @NonNull Integer staffId) {
        val staff = staffService.findById(staffId);
        staff.setAbsence(true);
        if (leaveInfo.getId() == null) {
            leaveInfo.setIsDel(false);
            leaveInfo.setCreateTime(new Date());
            leaveInfo.setStaff(staff);
            leaveInfoService.save(leaveInfo);
        } else {
            val info = leaveInfoService.findById(leaveInfo.getId());
            info.setContent(leaveInfo.getContent());
            info.setRemark(leaveInfo.getRemark());
            info.setTitle(leaveInfo.getTitle());
            info.setType(leaveInfo.getType());
            info.setStaff(staff);
            leaveInfoService.update(info);
        }
        staffService.update(staff);
        return "redirect:/staff/show";
    }

    @GetMapping("/delete")
    @Transactional
    public String delete(@RequestParam("id") Integer id) {
        leaveInfoService.deleteById(id);
        return "redirect:list";
    }

    public static void main(String[] args) {
        LeaveInfoEnum[] values = LeaveInfoEnum.values();

        System.out.printf(values[0].getType()+":"+values[0].getDescription());
    }
}
