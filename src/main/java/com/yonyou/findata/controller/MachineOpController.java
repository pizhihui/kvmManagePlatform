package com.yonyou.findata.controller;

import com.yonyou.findata.model.MachineInfo;
import com.yonyou.findata.service.MachineInfoService;
import com.yonyou.findata.service.VirtMachineOpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 机器操作
 * @author: pizhihui
 * @datae: 2017-07-12
 */
@Controller
@RequestMapping( value = "/machine")
public class MachineOpController extends BaseController{

    @Autowired
    private MachineInfoService machineInfoService;
    @Autowired
    private VirtMachineOpService machineOpService;


    @RequestMapping(value = "getAllMachine", method = RequestMethod.GET)
    @ResponseBody
    public List<MachineInfo> getAllMachine(String hostIp) {
        List<MachineInfo> virtMachines = machineInfoService.getVirtMachine(hostIp);

        return virtMachines;
    }

    /**
     * 进入tab页面
     */
    @RequestMapping(value = "machineOpIndex", method = RequestMethod.GET)
    public String machineOpIndex(HttpServletRequest request, HttpServletResponse response, Model model) {

        return "/machine/machineOpIndex";
    }

    /**
     * 进入虚拟机列表页面
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String listMachines(HttpServletRequest request, HttpServletResponse response, Model model) {
        List<MachineInfo> allInfos = new ArrayList<MachineInfo>();
        List<MachineInfo> allHostMachines = machineInfoService.getAllHostMachines();
        for (MachineInfo info : allHostMachines) {
            List<MachineInfo> virtMachine = machineInfoService.getVirtMachine(info.getHostIp());
            allInfos.addAll(virtMachine);
        }

        model.addAttribute("virtMachines", allInfos);
        return "/machine/machineList";
    }

    /**
     * 进入安装虚拟机页面
     */
    @RequestMapping(value = "installVirtMachine", method = RequestMethod.GET)
    public String installVirtMachine(HttpServletRequest request, HttpServletResponse response, Model model) {

        return "/machine/machineInstall";
    }

    /**
     * 进入添加物理机页面
     */
    @RequestMapping(value = "addHostMachine", method = RequestMethod.GET)
    public String addHostMachine(HttpServletRequest request, HttpServletResponse response, Model model) {
        //machineInfoService.addMachine(info);
        return "/machine/machineHostAdd";
    }

    /**
     * 添加物理机操作
     */
    @RequestMapping(value = "addHostMachineOp", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addHostMachineOp(HttpServletRequest request, HttpServletResponse response, Model model,
                                 MachineInfo info) {
        Map<String, Object> result = new HashMap<>();
        try {
            info.setType(0);
            info.setState(null);
            machineInfoService.addMachine(info);
            result.put("code", "202");
            result.put("msg", "success");
            //result = successMessage("successs");
            System.out.println(info);
        } catch (Exception e) {
            result.put("code", "404");
            result.put("msg", e.getMessage());
        }

        return result;
    }


}
