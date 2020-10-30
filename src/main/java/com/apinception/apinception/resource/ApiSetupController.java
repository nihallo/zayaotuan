package com.apinception.apinception.resource;

import com.apinception.apinception.common.ResultBase;
import com.apinception.apinception.model.ApiSetup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.apinception.apinception.repository.ApiSetupRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class ApiSetupController {

    @Autowired
    private ApiSetupRepository repository;

    @PostMapping("/addApiSetup")
    public ResultBase<Boolean> saveApiSetup(@RequestBody ApiSetup apiSetup){
        ApiSetup save = repository.save(apiSetup);
        ResultBase<Boolean> resultBase = new ResultBase<>();
        resultBase.setSuccess(true);
        return resultBase;
    }

    @GetMapping("/getAllApiSetups")
    public ResultBase<List<ApiSetup>> getApiSetups(){
        List<ApiSetup> all = repository.findAll();
        ResultBase<List<ApiSetup>> resultBase = new ResultBase<>(all);
            return resultBase;
    }

    @GetMapping("/findApiSetup/{id}")
    public ResultBase<ApiSetup> getApiSetup(@PathVariable int id){
        Optional<ApiSetup> byId = repository.findById(id);
        ApiSetup apiSetup = byId.get();
        ResultBase<ApiSetup> resultBase = new ResultBase<>(apiSetup);
        return resultBase;
    }

    @DeleteMapping("/deleteApiSetup/{id}")
    public  ResultBase<Boolean> deleteApiSetup (@PathVariable int id){
         repository.deleteById(id);
         ResultBase<Boolean> resultBase = new ResultBase<>();
         resultBase.setSuccess(true);
         return resultBase;
    }
}
