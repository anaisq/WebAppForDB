package com.awbd.onlinestoremvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
    @RequestMapping({"","/","/store"})
    public ModelAndView getHome(){

        return new ModelAndView("main");
    }

    @GetMapping("/login")
    public String showLogInForm(@RequestParam(required = false) String logout){ return "login"; }

//    @GetMapping("/login")
//    public String myLogout(@RequestParam string){ return "login"; }

    @GetMapping("/access_denied")
    public String accessDeniedPage(){ return "accessDenied"; }
}
