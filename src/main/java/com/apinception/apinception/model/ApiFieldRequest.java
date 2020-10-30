package com.apinception.apinception.model;

import lombok.Data;


import java.util.List;

@Data
public class ApiFieldRequest {

    private List<ApiField> apiFieldList;

    private String id;

    private String fieldName;

}
