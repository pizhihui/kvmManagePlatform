package com.yonyou.findata.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description
 * @Author pizhihui
 * @Date 2017/4/17
 */
@Controller
public class IndexController {


    @RequestMapping("/hello")
    public String hello(ModelMap map) {
        map.addAttribute("hello", "testpage");
        map.addAttribute("hotdeploy", "second test");
        map.addAttribute("hotdeploy2", "second test2");
        map.addAttribute("hotdeploy3", "second test33333");
        map.addAttribute("hotdeploy4", "second test77777");


        return "hello";
    }


    @RequestMapping("/")
    public String index(ModelMap map) {
        map.addAttribute("host", "http://blog.didispace.com");
        return "index";
    }

}
