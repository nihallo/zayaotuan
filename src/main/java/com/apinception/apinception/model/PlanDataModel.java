package com.apinception.apinception.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection="PlanDetailList")
public class PlanDataModel {

    private String alb;

    private String PlanBaseOnPrem;

    private String SA;

    private String Benefit;

    private String Libility;

    private String PremBreakdown;

}
