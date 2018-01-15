package com.fredchen.checkin.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: fredchen
 * @Date: 2018/1/15 17:58
 */

@Controller
@RequestMapping("/staff")
public class StaffController {

    @GetMapping("/list")
    public Object list(){

        return "list";
    }

}
