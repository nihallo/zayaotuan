package com.apinception.apinception.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

import java.util.List;

@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ApiField {

    private String fieldId;
    private String fieldName;
    private String fieldType;
    private String tableName;
    private String columnName;

    List<PropertiesModel> propertiesModelList;

}
