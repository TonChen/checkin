package com.fredchen.checkin.web;

import com.fredchen.checkin.base.BaseController;
import com.fredchen.checkin.domain.ClassRoom;
import com.fredchen.checkin.domain.SystemConfig;
import com.fredchen.checkin.service.IClassRoomService;
import com.fredchen.checkin.service.ISystemConfigService;
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
@RequestMapping("/config")
public class SystemConfigController extends BaseController {

    @Autowired
    private ISystemConfigService configService;

    @GetMapping("/list")
    public String list(Model model) {
        val configs = configService.findByIsDel(false);
        model.addAttribute("configs", configs);
        return "config/list";
    }

    @GetMapping("/create")
    public String create(Model model) {
        return "config/create";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") Integer id, Model model) {
        val config = configService.findById(id);
        model.addAttribute("config", config);
        return "config/update";
    }

    @PostMapping("/saveOrUpdate")
    public String saveOrUpdate(SystemConfig systemConfig) {
        if (systemConfig.getId() == null) {
            systemConfig.setIsDel(false);
            systemConfig.setCreateTime(new Date());
        } else {
            val config = configService.findById(systemConfig.getId());
            config.setName(systemConfig.getName());
            config.setCode(systemConfig.getCode());
            config.setType(systemConfig.getType());
            config.setValue(systemConfig.getValue());
        }
        configService.save(systemConfig);
        return "redirect:list";
    }

    @GetMapping("/delete")
    @Transactional
    public String delete(@RequestParam("id") Integer id) {
        configService.deleteById(id);
        return "redirect:list";
    }


}
