package com.example.databaseconnector.controller;

import com.example.databaseconnector.bean.ScriptInfo;
import com.example.databaseconnector.bean.ScriptRequest;
import com.example.databaseconnector.service.ScriptService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ScriptController {

    @Resource
    private ScriptService scriptService;

    /**
     * 查询脚本
     *
     * @param request
     */
    @RequestMapping(value = "/queryScript")
    public ScriptInfo queryScript(@RequestBody ScriptRequest request) {
        ScriptInfo scriptInfo = new ScriptInfo();
        scriptInfo.setScriptName(request.getScriptName());
        scriptInfo.setDataSource(request.getDataSourceType());
        scriptInfo.setUserId("1");
        return scriptService.queryScript(scriptInfo);
    }
}
