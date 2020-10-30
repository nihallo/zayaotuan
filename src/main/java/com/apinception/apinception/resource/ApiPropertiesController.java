package com.apinception.apinception.resource;

import com.apinception.apinception.common.ResultBase;
import com.apinception.apinception.model.ApiField;
import com.apinception.apinception.model.ApiSetup;
import com.apinception.apinception.model.PropertiesModel;
import com.apinception.apinception.model.PropertiesModelRequest;
import com.apinception.apinception.repository.ApiSetupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class ApiPropertiesController {

    @Autowired
    private ApiSetupRepository apiSetupRepository;

    @PostMapping("/addProperties")
    public ResultBase<Boolean> addProperties(@RequestBody PropertiesModelRequest propertiesModelRequest){
        Optional<ApiSetup> byId = apiSetupRepository.findById(propertiesModelRequest.getApiId());
        ApiSetup apiSetup = byId.get();
        List<ApiField> apiFieldsList = apiSetup.getApiFieldsList();
        if (!CollectionUtils.isEmpty(apiFieldsList)){
            for (ApiField apiField : apiFieldsList){
                List<PropertiesModel> list = new ArrayList<>();
                if (apiField.getFieldId().equals(propertiesModelRequest.getFieldId())){
                    list.addAll(propertiesModelRequest.getPropertiesModelList());
                }
                apiField.setPropertiesModelList(list);
            }
            apiSetup.setApiFieldsList(apiFieldsList);
        }
        ApiSetup save = apiSetupRepository.save(apiSetup);
        ResultBase<Boolean> resultBase = new ResultBase<>();
        resultBase.setSuccess(true);
        return resultBase;
    }

    @PostMapping("/updateProperties")
    public ResultBase<Boolean> updateProperties(PropertiesModelRequest propertiesModelRequest){
        Optional<ApiSetup> byId = apiSetupRepository.findById(propertiesModelRequest.getApiId());
        ApiSetup apiSetup = byId.get();
        List<ApiField> apiFieldsList = apiSetup.getApiFieldsList();
        if (CollectionUtils.isEmpty(apiFieldsList)){
            for (ApiField apiField : apiFieldsList){
                List<PropertiesModel> list = new ArrayList<>();
                if (apiField.getFieldId().equals(propertiesModelRequest.getFieldId())){
                    List<PropertiesModel> propertiesModelList = apiField.getPropertiesModelList();
                    for (PropertiesModel propertiesModel : propertiesModelList){

                    }
                }
                apiField.setPropertiesModelList(list);
            }
            apiSetup.setApiFieldsList(apiFieldsList);
        }
        return null;
    }

}
