package com.apinception.apinception.repository;

import com.apinception.apinception.model.ApiProcessing;
import com.apinception.apinception.model.PlanDataModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ApiPlanDataRepository extends MongoRepository<PlanDataModel, String> {

    public PlanDataModel findAllByAlbEquals(String alb);

    public PlanDataModel findByAlbEquals(String alb);

    public PlanDataModel findByAlbIn(String alb);

}
