package com.example.databaseconnector.bean;

import lombok.Data;

@Data
public class ScriptRequest {
    private String scriptName;
    private String dataSourceType;
    private String context;
    private String executeTimes;
    private String startTime;
    private String action;
}
