package com.apinception.apinception.repository;

import com.apinception.apinception.model.ApiSetup;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ApiSetupRepository extends MongoRepository<ApiSetup, Integer> {
}
