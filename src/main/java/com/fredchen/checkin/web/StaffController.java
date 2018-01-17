package com.fredchen.checkin.web;

import com.fredchen.checkin.service.IStaffService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: fredchen
 * @Date: 2018/1/15 17:58
 */

@Controller
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private IStaffService staffService;

    @GetMapping("/list")
    public Object list(@RequestParam("depId") Integer depId, Model model){

        val staffs = staffService.withDepartmentId(depId);
        model.addAttribute("staffs", staffs);

        return "staff/list";
    }

}
