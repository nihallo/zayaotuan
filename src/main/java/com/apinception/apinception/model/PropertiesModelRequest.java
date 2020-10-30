package com.apinception.apinception.model;

import lombok.Data;

import java.util.List;

@Data
public class PropertiesModelRequest {

    private String fieldId;

    private String apiId;

    private String id;

    private List<PropertiesModel> propertiesModelList;

}
