package com.apinception.apinception.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ApiFieldRequestOne {

    private String id;
    private String fieldName;
    private String fieldType;
    private String tableName;
    private String columnName;

}
