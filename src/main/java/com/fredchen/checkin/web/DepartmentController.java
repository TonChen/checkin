package com.fredchen.checkin.web;

import com.fredchen.checkin.base.BaseController;
import com.fredchen.checkin.domain.Department;
import com.fredchen.checkin.service.impl.DepartmentService;
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
@RequestMapping("/dep")
public class DepartmentController extends BaseController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/list")
    public String list(Model model) {
        val deps = departmentService.findByIsDel(false);
        model.addAttribute("deps", deps);
        return "department/list";
    }


    @GetMapping("/create")
    public String create(Model model) {
        return "department/create";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") Integer id, Model model) {
        val dep = departmentService.findById(id);
        model.addAttribute("dep",dep);
        return "department/update";
    }

    @PostMapping("/saveOrUpdate")
    public String saveOrUpdate(Department department) {
        if(department.getId() == null){
            department.setIsDel(false);
            department.setCreateTime(new Date());
            departmentService.save(department);
        }else{
            val dep = departmentService.findById(department.getId());
            dep.setDescription(department.getDescription());
            dep.setName(department.getName());
            departmentService.update(dep);
        }
        return "redirect:list";
    }

    @GetMapping("/delete")
    @Transactional
    public String delete(@RequestParam("id") Integer id) {
        departmentService.deleteById(id);
        return "redirect:list";
    }


}
