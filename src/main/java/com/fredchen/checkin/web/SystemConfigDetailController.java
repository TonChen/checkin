package com.fredchen.checkin.web;

import com.fredchen.checkin.base.BaseController;
import com.fredchen.checkin.domain.SystemConfigDetail;
import com.fredchen.checkin.service.ISystemConfigDetailService;
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
@RequestMapping("/configDetail")
public class SystemConfigDetailController extends BaseController {

    @Autowired
    private ISystemConfigDetailService configDetailService;
    @Autowired
    private ISystemConfigService systemConfigService;

    @GetMapping("/list")
    public Object list(@RequestParam(name = "configId") Integer configId, Model model) {
        val configs = systemConfigService.findByIsDel(false);
        val details = configDetailService.queryByConfigId(configId);
        model.addAttribute("configs", configs);
        model.addAttribute("details", details);
        model.addAttribute("configId", configId);
        return "configDetail/list";
    }


    @GetMapping("/create")
    public String create(Model model, @RequestParam(name = "configId") Integer configId) {
        val configs = systemConfigService.findByIsDel(false);
        model.addAttribute("configs", configs);
        model.addAttribute("configId", configId);
        return "configDetail/create";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") Integer id, @RequestParam(name = "configId") Integer configId, Model model) {
        val detail = configDetailService.findById(id);
        val configs = systemConfigService.findByIsDel(false);
        model.addAttribute("detail", detail);
        model.addAttribute("configs", configs);
        model.addAttribute("configId", configId);
        return "configDetail/update";
    }

    @PostMapping("/saveOrUpdate")
    public String saveOrUpdate(SystemConfigDetail configDetail, @RequestParam("configId") Integer configId, @RequestParam("systemConfigId") Integer systemConfigId) {
        val systemConfig = systemConfigService.findById(systemConfigId);
        configDetail.setType(systemConfig.getType());
        configDetail.setCode(systemConfig.getCode());
        if (configDetail.getId() == null) {
            configDetail.setIsDel(false);
            configDetail.setCreateTime(new Date());
            configDetail.setSystemConfig(systemConfig);
            configDetailService.save(configDetail);
        } else {
            val detail = configDetailService.findById(configDetail.getId());
            detail.setName(configDetail.getName());
            detail.setExtra(configDetail.getExtra());
            detail.setRemark(configDetail.getRemark());
            detail.setValue(configDetail.getValue());
            detail.setSystemConfig(systemConfig);
            configDetailService.update(detail);
        }
        return "redirect:list?configId=" + configId;
    }

    @GetMapping("/delete")
    @Transactional
    public String delete(@RequestParam("id") Integer id, @RequestParam("configId") Integer configId) {
        configDetailService.deleteById(id);
        return "redirect:list?configId=" + configId;
    }


}
