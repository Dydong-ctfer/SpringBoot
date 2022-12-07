package com.dyd.springboot03.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

@Controller
public class IndexController {
    @RequestMapping("/test")
    public String test(Model model)
    {
        model.addAttribute("msg","hello_springboot~");
        model.addAttribute("users", Arrays.asList("dydong","sniper"));
        return "test";
    }
}
