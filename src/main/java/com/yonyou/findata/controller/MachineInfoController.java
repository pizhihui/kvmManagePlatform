package com.yonyou.findata.controller;

import com.yonyou.findata.model.MachineInfo;
import com.yonyou.findata.service.MachineInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: pizhihui
 * @datae: 2017-07-12
 */
@Controller
@RequestMapping( value = "/machine")
public class MachineInfoController {

    @Autowired
    private MachineInfoService machineInfoService;


    @RequestMapping(value = "getAllMachine", method = RequestMethod.GET)
    @ResponseBody
    public MachineInfo getAllMachine(Long id) {
        MachineInfo machineInfo = machineInfoService.getMachineInfoById(id);

        return machineInfo;

    }

}
