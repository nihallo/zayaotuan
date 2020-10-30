package com.apinception.apinception.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ApiField {
    private String fieldName;
    private String fieldType;
    private String tableName;
    private String columnName;

}
