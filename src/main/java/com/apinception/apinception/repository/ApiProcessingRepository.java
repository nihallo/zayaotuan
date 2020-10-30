package com.apinception.apinception.repository;

import com.apinception.apinception.model.ApiProcessing;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ApiProcessingRepository extends MongoRepository<ApiProcessing, String> {

    public ApiProcessing findAllByApiIdEquals(String apiId);
}
