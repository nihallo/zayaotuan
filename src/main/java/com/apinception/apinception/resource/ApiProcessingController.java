package com.apinception.apinception.resource;

import com.apinception.apinception.common.ResultBase;
import com.apinception.apinception.model.ApiProcessing;
import com.apinception.apinception.model.ApiProcessingStep;
import com.apinception.apinception.model.ApiSetup;
import com.apinception.apinception.repository.ApiProcessingRepository;
import com.apinception.apinception.repository.ApiSetupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ApiProcessingController {
    @Autowired
    private ApiProcessingRepository repository;

    @PostMapping("/addApiProcessing")
    public ResultBase<Boolean> saveApiSetup(@RequestBody ApiProcessing apiProcessing){
        ApiProcessing allByApiIdEquals = repository.findAllByApiIdEquals(apiProcessing.getApiId());
        ResultBase<Boolean> resultBase = new ResultBase<>();
        if (null != allByApiIdEquals){
            List<ApiProcessingStep> apiProcessingStepList = allByApiIdEquals.getApiProcessingStepList();
            if (CollectionUtils.isEmpty(apiProcessingStepList)){
                ApiProcessing save = repository.save(apiProcessing);
                resultBase.setSuccess(true);
                return resultBase;
            }else {
                apiProcessingStepList.addAll(apiProcessing.getApiProcessingStepList());
                allByApiIdEquals.setApiProcessingStepList(apiProcessingStepList);
                repository.save(apiProcessing);
                resultBase.setSuccess(true);
                return resultBase;
            }
        }else {
            repository.save(apiProcessing);
            resultBase.setSuccess(true);
            return resultBase;
        }

    }

    @GetMapping("/getAllApiProcessings")
    public ResultBase<List<ApiProcessing>> getApiProcessings(){
        List<ApiProcessing> all = repository.findAll();
        ResultBase<List<ApiProcessing>> resultBase = new ResultBase<>(all);
        return resultBase;
    }

    @GetMapping("/findApiProcessing/{apiId}")
    public ResultBase<ApiProcessing> getApiProcessing(@PathVariable String apiId){
        ApiProcessing allByApiIdEquals = repository.findAllByApiIdEquals(apiId);
        ResultBase<ApiProcessing> resultBase = new ResultBase<>(allByApiIdEquals);
        return resultBase;
    }

    @DeleteMapping("/deleteApiProcessing/{id}")
    public  ResultBase<Boolean> deleteApiProcessing (@PathVariable String id){
        repository.deleteById(id);
        ResultBase<Boolean> resultBase = new ResultBase<>();
        resultBase.setSuccess(true);
        return resultBase;
    }
}
