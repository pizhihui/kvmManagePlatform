package com.yonyou.findata.controller;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: pizhihui
 * @datae: 2017-07-19
 */
public class BaseController {


    public Map<String, Object> errorMessage(Object e) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", "404");
        result.put("msg", e);
        return result;
    }

    public Map<String, Object> successMessage(Object e) {
        Map<String, Object> result= new HashMap<>();
        result.put("code", "202");
        result.put("msg", e);
        return result;
    }

}
