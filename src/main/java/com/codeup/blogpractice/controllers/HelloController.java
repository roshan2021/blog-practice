package com.codeup.blogpractice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
//    @GetMapping("/hello")
//    @ResponseBody
//    public String hello(){
//        return "Hello from spring";
//    }

    @GetMapping("/hello/{name}")
    public String sayHello(@PathVariable String name, Model model){
        model.addAttribute("sayHelloName", name);
        return "hello";
    }

}
