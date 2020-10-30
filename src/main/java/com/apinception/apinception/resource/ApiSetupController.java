package com.apinception.apinception.resource;

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
    public String saveApiSetup(@RequestBody ApiSetup apiSetup){
        repository.save(apiSetup);
        return "api setup saved.";
    }

    @GetMapping("/getAllApiSetups")
    public List<ApiSetup> getApiSetups(){
        return repository.findAll();
    }

    @GetMapping("/findApiSetup/{id}")
    public Optional<ApiSetup> getApiSetup(@PathVariable int id){
        return repository.findById(id);
    }

    @DeleteMapping("/deleteApiSetup/{id}")
    public  String deleteApiSetup (@PathVariable int id){
         repository.deleteById(id);
         return "book deleted.";
    }
}
