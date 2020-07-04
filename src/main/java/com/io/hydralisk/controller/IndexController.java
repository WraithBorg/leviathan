package com.io.hydralisk.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @RequestMapping("/")
    public String index(){
        return "this is <H1>index </H1> page。";
    }
    @RequestMapping("/star")
    public String star(){
        return "this is index <H1>star </H1>page。";
    }
}
