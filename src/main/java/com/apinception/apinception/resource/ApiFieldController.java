package com.apinception.apinception.resource;

import com.apinception.apinception.common.ResultBase;
import com.apinception.apinception.model.ApiField;
import com.apinception.apinception.model.ApiFieldRequest;
import com.apinception.apinception.model.ApiFieldRequestOne;
import com.apinception.apinception.model.ApiSetup;
import com.apinception.apinception.repository.ApiSetupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class ApiFieldController {



    @Autowired
    private ApiSetupRepository repository;


    /**
     * 对ApiSetup添加apiFieldList
     * @param apiFieldRequest
     * @return
     */
    @PostMapping("/addOrUpdateFieldList")
    public ResultBase<Boolean> addFieldList(@RequestBody ApiFieldRequest apiFieldRequest){
        ResultBase<Boolean> resultBase = new ResultBase<>();
        if (apiFieldRequest.getId() != null && apiFieldRequest.getApiFieldList() != null && apiFieldRequest.getApiFieldList().size() > 0){
            Optional<ApiSetup> byId = repository.findById(apiFieldRequest.getId());
            ApiSetup apiSetup = byId.get();
            apiSetup.setApiFieldsList(null);
            apiSetup.setApiFieldsList(apiFieldRequest.getApiFieldList());
            ApiSetup save = repository.save(apiSetup);
            resultBase.setSuccess(true);
            return resultBase;
        }else {
            resultBase.setMsg("不存在step或者ApiFieldList为空");
            resultBase.setSuccess(false);
            return resultBase;
        }

    }

    @PostMapping("/addOrUpdateField")
    public ResultBase<Boolean> addField(@RequestBody ApiFieldRequestOne apiFieldRequestOne){
        String id = apiFieldRequestOne.getId();
        Optional<ApiSetup> byId = repository.findById(apiFieldRequestOne.getId());
        ApiSetup apiSetup = byId.get();
        List<ApiField> apiFieldsList = apiSetup.getApiFieldsList();
        if (CollectionUtils.isEmpty(apiFieldsList)){
            apiFieldsList = new ArrayList<>();
            apiFieldsList.add(this.convert(apiFieldRequestOne));
            apiSetup.setApiFieldsList(apiFieldsList);
            repository.save(apiSetup);
        }else {
            apiFieldsList.add(this.convert(apiFieldRequestOne));
            apiSetup.setApiFieldsList(apiFieldsList);
            repository.save(apiSetup);
        }
        ResultBase<Boolean> resultBase = new ResultBase<>();
        resultBase.setSuccess(true);
        return resultBase;
    }

    @PostMapping("/apiFieldList")
    public ResultBase<List<ApiField>> getApiField(@RequestBody ApiFieldRequest apiFieldRequest){
        Optional<ApiSetup> byId = repository.findById(apiFieldRequest.getId());
        ApiSetup apiSetup = byId.get();
        List<ApiField> apiFieldsList = apiSetup.getApiFieldsList();
        if (!CollectionUtils.isEmpty(apiFieldsList)){
            if (!StringUtils.isEmpty(apiFieldRequest.getFieldName())){
                List<ApiField> collect = apiFieldsList.stream()
                        .filter(apiField -> apiField.getFieldName().equals(apiFieldRequest.getFieldName()))
                        .collect(Collectors.toList());
                apiFieldsList.clear();
                apiFieldsList.addAll(collect);
            }
        }else {
            apiFieldsList = new ArrayList<>();
        }
        ResultBase<List<ApiField>> resultBase = new ResultBase<>(apiFieldsList);
        resultBase.setSuccess(true);
        return resultBase;
    }

    private ApiField convert(ApiFieldRequestOne apiFieldRequestOne){
        ApiField apiField = new ApiField();
        apiField.setFieldId(UUID.randomUUID().toString());
        apiField.setFieldName(apiFieldRequestOne.getFieldName());
        apiField.setColumnName(apiFieldRequestOne.getColumnName());
        apiField.setTableName(apiFieldRequestOne.getTableName());
        apiField.setFieldType(apiFieldRequestOne.getFieldType());
        return apiField;
    }
}
