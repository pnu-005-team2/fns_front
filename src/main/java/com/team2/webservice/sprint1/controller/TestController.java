package com.team2.webservice.sprint1.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class TestController {
    @RequestMapping("/view_test")
    public String view(Model model)
    {
        return "../views/insert";
    }
}
