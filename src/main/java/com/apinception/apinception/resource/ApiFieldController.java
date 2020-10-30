package com.apinception.apinception.resource;

import com.apinception.apinception.common.ResultBase;
import com.apinception.apinception.model.ApiField;
import com.apinception.apinception.model.ApiFieldRequest;
import com.apinception.apinception.model.ApiSetup;
import com.apinception.apinception.repository.ApiSetupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
public class ApiFieldController {



    @Autowired
    private ApiSetupRepository repository;


    /**
     * 对ApiSetup添加apiFieldList
     * @param apiFieldRequest
     * @return
     */
    @PostMapping("/addFieldList")
    public ResultBase<Boolean> addFieldList(@RequestBody ApiFieldRequest apiFieldRequest){
        ResultBase<Boolean> resultBase = new ResultBase<>();
        if (apiFieldRequest.getStepId() != null && apiFieldRequest.getApiFieldList() != null && apiFieldRequest.getApiFieldList().size() > 0){
            Optional<ApiSetup> byId = repository.findById(apiFieldRequest.getStepId());
            ApiSetup apiSetup = byId.get();
            apiSetup.setApiFieldsList(apiFieldRequest.getApiFieldList());
            ApiSetup save = repository.save(apiSetup);
            resultBase.setSuccess(true);
            return resultBase;
        }else {
            resultBase.setSuccess(false);
            return resultBase;
        }

    }
}
