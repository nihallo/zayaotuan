package com.apinception.apinception.repository;

import com.apinception.apinception.model.ApiSetup;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ApiSetupRepository extends MongoRepository<ApiSetup, String> {

}
