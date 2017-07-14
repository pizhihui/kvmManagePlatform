package com.yonyou.findata.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @Desc
 * @Author pizhihui
 * @Date 2017/4/19
 */
public class WelcomeController {

    // inject via application.properties
    @Value("${welcome.message:test}")
    private String message = "Hello World";

    @RequestMapping("/")
    public String welcome(Map<String, Object> model) {
        model.put("message", this.message);
        return "welcome";
    }

}
