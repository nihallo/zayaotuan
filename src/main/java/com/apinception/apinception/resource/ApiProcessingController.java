package com.apinception.apinception.resource;

import com.apinception.apinception.common.ResultBase;
import com.apinception.apinception.model.ApiProcessing;
import com.apinception.apinception.model.ApiSetup;
import com.apinception.apinception.repository.ApiProcessingRepository;
import com.apinception.apinception.repository.ApiSetupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ApiProcessingController {
    @Autowired
    private ApiProcessingRepository repository;

    @PostMapping("/addApiProcessing")
    public ResultBase<Boolean> saveApiSetup(@RequestBody ApiProcessing apiProcessing){
        ApiProcessing save = repository.save(apiProcessing);
        ResultBase<Boolean> resultBase = new ResultBase<>();
        resultBase.setSuccess(true);
        return resultBase;
    }

    @GetMapping("/getAllApiProcessings")
    public ResultBase<List<ApiProcessing>> getApiProcessings(){
        List<ApiProcessing> all = repository.findAll();
        ResultBase<List<ApiProcessing>> resultBase = new ResultBase<>(all);
        return resultBase;
    }

    @GetMapping("/findApiProcessing/{apiId}")
    public ResultBase<ApiProcessing> getApiProcessing(@PathVariable int apiId){

        Optional<ApiProcessing> byId = repository.findOne(apiId);
        ApiProcessing apiProcessing = byId.get();
        ResultBase<ApiProcessing> resultBase = new ResultBase<>(apiProcessing);
        return resultBase;
    }

    @DeleteMapping("/deleteApiProcessing/{id}")
    public  ResultBase<Boolean> deleteApiProcessing (@PathVariable int id){
        repository.deleteById(id);
        ResultBase<Boolean> resultBase = new ResultBase<>();
        resultBase.setSuccess(true);
        return resultBase;
    }
}
