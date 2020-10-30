package com.apinception.apinception.model;

import lombok.Data;
import lombok.Getter;
import org.apache.catalina.LifecycleState;

import java.util.List;

@Data
public class ApiFieldRequest {

    private List<ApiField> apiFieldList;

    private Integer stepId;

}
