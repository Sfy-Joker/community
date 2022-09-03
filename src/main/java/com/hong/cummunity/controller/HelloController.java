package com.hong.cummunity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    @GetMapping(value = "/hello")
    public String toHello(@RequestParam(value = "name")String name, Model model){
        model.addAttribute("name", name);
        return "hello";
    }

}
