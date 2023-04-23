package com.example.databaseconnector.bean;

import lombok.Data;

@Data
public class ScriptInfo {
    private String scriptName;
    private String userId;
    private String dataSource;
    private String context;
    private String startTime;
    private int executeTimes;
    private String status;
    private String endTime;
}
