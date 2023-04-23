package com.example.databaseconnector.mapper;

import com.example.databaseconnector.bean.ScriptInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ScriptInfoMapper {
    List<ScriptInfo> queryAllScripts();

    ScriptInfo queryScript(@Param("scriptInfo") ScriptInfo scriptInfo);

    Integer addScript(@Param("scriptInfo") ScriptInfo scriptInfo);

    Integer updateScript(@Param("scriptInfo") ScriptInfo scriptInfo);

    Integer deleteScript(@Param("scriptInfo") ScriptInfo scriptInfo);

    List<ScriptInfo> queryUnexecutedTask(String now);
}
