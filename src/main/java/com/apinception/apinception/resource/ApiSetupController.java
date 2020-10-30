package com.apinception.apinception.resource;

import com.apinception.apinception.common.ResultBase;
import com.apinception.apinception.model.ApiSetup;
import com.apinception.apinception.model.ApiSetupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import com.apinception.apinception.repository.ApiSetupRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class ApiSetupController {

    @Autowired
    private ApiSetupRepository repository;

    @PostMapping("/addApiSetup")
    public ResultBase<Boolean> saveApiSetup(@RequestBody ApiSetup apiSetup){
        if (StringUtils.isEmpty(apiSetup.getId())){
            apiSetup.setId(UUID.randomUUID().toString());
        }
        ApiSetup save = repository.save(apiSetup);
        ResultBase<Boolean> resultBase = new ResultBase<>();
        resultBase.setSuccess(true);
        return resultBase;
    }

    @PostMapping("/getAllApiSetups")
    public ResultBase<List<ApiSetup>> getApiSetups(@RequestBody ApiSetupRequest apiSetup){
        List<ApiSetup> result = new ArrayList<>();
        List<ApiSetup> all = repository.findAll();
        result.addAll(all);
        if (!StringUtils.isEmpty(apiSetup.getApiName())){
            List<ApiSetup> collect = all.stream().filter(ap -> ap.getApiName().equals(apiSetup.getApiName())).collect(Collectors.toList());
            result.clear();
            result.addAll(collect);

        }
        if (!StringUtils.isEmpty(apiSetup.getApiEndpoint())){
            List<ApiSetup> collect = result.stream().filter(ap -> ap.getApiEndpoint().equals(apiSetup.getApiEndpoint())).collect(Collectors.toList());
            result.clear();
            result.addAll(collect);
        }

        ResultBase<List<ApiSetup>> resultBase = new ResultBase<>(result);
        return resultBase;
    }



    @GetMapping("/findApiSetup/{id}")
    public ResultBase<ApiSetup> getApiSetup(@PathVariable String id){
        Optional<ApiSetup> byId = repository.findById(id);
        ApiSetup apiSetup = byId.get();
        ResultBase<ApiSetup> resultBase = new ResultBase<>(apiSetup);
        return resultBase;
    }

    @DeleteMapping("/deleteApiSetup/{id}")
    public  ResultBase<Boolean> deleteApiSetup (@PathVariable String id){
         repository.deleteById(id);
         ResultBase<Boolean> resultBase = new ResultBase<>();
         resultBase.setSuccess(true);
         return resultBase;
    }
}
