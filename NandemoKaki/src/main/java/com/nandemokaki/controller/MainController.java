package com.nandemokaki.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/asd")
public class MainController {

    @RequestMapping
    public @ResponseBody String main() {
        return "Hello Woniper Spring Boot~";
    }
}
