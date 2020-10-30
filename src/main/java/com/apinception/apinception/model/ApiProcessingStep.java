package com.apinception.apinception.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiProcessingStep {
    private int stepNumber ;
    private String stepName;
    private String checkBeforeRun; // if true, run this step, if not, skip
    private String actionType; // 值 ： ADD_LIST, ADD_FIELD, VALIDATE, CONNECTOR
    private String dataLevel; // 值 ：LEVEL_1 LEVEL_2, LEVEL_3, lEVEL_4, LEVEL_5
    private String addToWhichListName;
    private String fieldNameListName;
    private String columnNames;
    private String method; // 值: CALCULATE,QUERY_DB, SEQUENCE
    private String formula;
    private String tableName;
    private String whereCluse;
    private String sequenceCode;
    private String errorType; //ERROR, WARNING
    private String errorMessage;
    private String connectorId; // KEY TO connector setup
}
