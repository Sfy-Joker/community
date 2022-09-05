package com.hong.cummunity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping(value = "/")
    public String toHello(String name, Model model){
        model.addAttribute("name", name);
        return "index";
    }

}
