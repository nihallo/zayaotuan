package com.apinception.apinception.repository;

import com.apinception.apinception.model.ApiProcessing;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ApiProcessingRepository extends MongoRepository<ApiProcessing, Integer> {
}
