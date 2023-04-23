package com.example.databaseconnector.service;

import com.example.databaseconnector.bean.ScriptInfo;
import com.example.databaseconnector.mapper.ScriptInfoMapper;
import com.example.databaseconnector.utils.TimeUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ScriptService {

    @Resource
    private ScriptInfoMapper scriptInfoMapper;

    /**
     * 查询脚本信息
     *
     * @param scriptInfo scriptInfo
     * @return ScriptInfo
     */
    public ScriptInfo queryScript(ScriptInfo scriptInfo) {
        return scriptInfoMapper.queryScript(scriptInfo);
    }


    /**
     * 获取未执行的任务
     *
     * @return List<ScriptInfo>
     */
    public List<ScriptInfo> getUnexecutedTask() {
        return scriptInfoMapper.queryUnexecutedTask(TimeUtils.getCurrentTime());
    }

    /**
     * 处理脚本
     *
     * @param scriptInfo scriptInfo
     * @param action     action
     * @return 执行结果
     */
    public int handleScript(ScriptInfo scriptInfo, String action) {
        int result = 0;
        switch (action) {
            case "add":
                result = scriptInfoMapper.addScript(scriptInfo);
                break;
            case "update":
                result = scriptInfoMapper.updateScript(scriptInfo);
                break;
            case "delete":
                result = scriptInfoMapper.deleteScript(scriptInfo);
                break;
        }
        return result;
    }
}
