package com.apinception.apinception.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.apinception.apinception.common.ActionCommon;
import com.apinception.apinception.model.ApiProcessingStep;
import com.apinception.apinception.model.PlanDataModel;
import com.apinception.apinception.repository.ApiPlanDataRepository;
import com.apinception.apinception.service.CommonQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class CommonQueryServiceImpl implements CommonQueryService {

    @Autowired
    private ApiPlanDataRepository apiPlanDataRepository;

    @Override
    public JSONObject addFieldAndFormula(ApiProcessingStep apiProcessingStep,JSONObject jsonObject) {
        // 执行公式
        String formula = apiProcessingStep.getFormula();
        String dataLevel = apiProcessingStep.getDataLevel();
        int dateOfBirth = formula.indexOf("dateOfBirth");
        if (dateOfBirth > 0){
            if (dataLevel.equals(ActionCommon.LEVEL_1)){
                String fieldNameListName = apiProcessingStep.getFieldNameListName();
                jsonObject.put(fieldNameListName,formula);
            }
        }
        int i = formula.indexOf("7%");
        if (i > 0){
            if (dataLevel.equals(ActionCommon.LEVEL_2)){
                Integer premBreakdown = Integer.valueOf(jsonObject.get("PremBreakdown").toString());
                BigDecimal bigDecimal = new BigDecimal(premBreakdown);
                String fieldNameListName = apiProcessingStep.getFieldNameListName();
                String addToWhichListName = apiProcessingStep.getAddToWhichListName();
                jsonObject.getJSONObject(addToWhichListName).put(fieldNameListName,formula);
            }
        }
        return jsonObject;
    }

    @Override
    public JSONObject addFieldAndSequence(ApiProcessingStep apiProcessingStep,JSONObject jsonObject) {
        String dataLevel = apiProcessingStep.getDataLevel();
        if (dataLevel.equals(ActionCommon.LEVEL_1)) {
            String fieldNameListName = apiProcessingStep.getFieldNameListName();
            jsonObject.put(fieldNameListName, UUID.randomUUID().toString());
        }
        return jsonObject;
    }

    @Override
    public JSONObject addFieldListAndQueryDb(ApiProcessingStep apiProcessingStep,JSONObject jsonObject) {
        // 执行公式

        String dataLevel = apiProcessingStep.getDataLevel();
        String whereCluse = apiProcessingStep.getWhereCluse();
        String[] split = whereCluse.split(".");
        String alb = jsonObject.get(split[1]).toString();
        PlanDataModel allByAlbEquals = apiPlanDataRepository.findAllByAlbEquals(alb);
        if (dataLevel.equals(ActionCommon.LEVEL_1)){
            String fieldNameListName = apiProcessingStep.getFieldNameListName();
            jsonObject.put(fieldNameListName,allByAlbEquals);
        }else if (dataLevel.equals(ActionCommon.LEVEL_2)){
            String fieldNameListName = apiProcessingStep.getFieldNameListName();
            jsonObject.put(fieldNameListName,allByAlbEquals);
        }
        return null;
    }

    @Override
    public boolean addValidateAndFormula(ApiProcessingStep apiProcessingStep, JSONObject jsonObject) {
        String[] split = apiProcessingStep.getFormula().split(".");
        String[] split1 = split[1].split(">");

        Integer integer = Integer.valueOf(jsonObject.get(split1[0]).toString());
        Integer integer1 = Integer.valueOf(split1[1].toString());
        if (integer > integer1){
            return true;
        }else {
            return false;
        }
    }
}
