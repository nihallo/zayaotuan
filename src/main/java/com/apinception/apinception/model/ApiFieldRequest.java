package com.apinception.apinception.model;

import lombok.Data;
import lombok.Getter;
import org.apache.catalina.LifecycleState;

import java.util.List;

public class ApiFieldRequest {

    private List<ApiField> apiFieldList;

    private Integer stepId;

    public List<ApiField> getApiFieldList() {
        return apiFieldList;
    }

    public void setApiFieldList(List<ApiField> apiFieldList) {
        this.apiFieldList = apiFieldList;
    }

    public Integer getStepId() {
        return stepId;
    }

    public void setStepId(Integer stepId) {
        this.stepId = stepId;
    }
}
