package com.fredchen.checkin.web;

import com.fredchen.checkin.service.impl.DepartmentService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: fredchen
 * @Date: 2018/1/16 13:42
 */

@Controller
@RequestMapping("/dep")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/list")
    public Object list(Model model){
        val deps = departmentService.findByIsDel(0);
        model.addAttribute("deps", deps);
        return "department/list";
    }





}
