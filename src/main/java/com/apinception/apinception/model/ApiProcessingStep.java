package com.apinception.apinception.model;

public class ApiProcessingStep {
    private int stepNumber ;
    private String stepName;
    private String checkBeforeRun;
    private String actionType; // 值 ： ADD_LIST, VALIDATE, CONNECTOR
    private String dataLevel; // 值 ：LEVEL_1 LEVEL_2, LEVEL_3, lEVEL_4, LEVEL_5
    private String addToWhichListName;
    private String fieldNameListName;
    private String columnNames;
    private String method; // 值: CALCULATE,QUERY_DB, SEQUENCE
    private String formula;
    private String tableName;
    private String whereCluse;
    private String sequenceCode;
    private String errorType;
    private String errorMessage;
    private String connectorID;
}
