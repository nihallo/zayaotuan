package com.apinception.apinception.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.apinception.apinception.common.ActionCommon;
import com.apinception.apinception.model.ApiProcessingStep;
import com.apinception.apinception.service.CommonQueryService;

public class CommonQueryServiceImpl implements CommonQueryService {


    @Override
    public JSONObject addFieldAndFormula(ApiProcessingStep apiProcessingStep,JSONObject jsonObject) {
        // 执行公式
        String formula = apiProcessingStep.getFormula();
        String dataLevel = apiProcessingStep.getDataLevel();
        if (dataLevel.equals(ActionCommon.LEVEL_1)){
            String fieldNameListName = apiProcessingStep.getFieldNameListName();
            jsonObject.put(fieldNameListName,formula);
        }else if (dataLevel.equals(ActionCommon.LEVEL_2)){
            String fieldNameListName = apiProcessingStep.getFieldNameListName();
            String addToWhichListName = apiProcessingStep.getAddToWhichListName();
            jsonObject.getJSONObject(addToWhichListName).put(fieldNameListName,formula);
        }
        return jsonObject;
    }

    @Override
    public JSONObject addFieldAndQueryDb(ApiProcessingStep apiProcessingStep,JSONObject jsonObject) {
        // 执行公式

//        String dataLevel = apiProcessingStep.getDataLevel();
//        if (dataLevel.equals(ActionCommon.LEVEL_1)){
//            String fieldNameListName = apiProcessingStep.getFieldNameListName();
//            jsonObject.put(fieldNameListName,formula);
//        }else if (dataLevel.equals(ActionCommon.LEVEL_2)){
//            String fieldNameListName = apiProcessingStep.getFieldNameListName();
//            String addToWhichListName = apiProcessingStep.getAddToWhichListName();
//            jsonObject.getJSONObject(addToWhichListName).put(fieldNameListName,formula);
//        }
        return null;
    }

    @Override
    public JSONObject addFieldListAndQueryDb(ApiProcessingStep apiProcessingStep,JSONObject jsonObject) {
        return null;
    }

    @Override
    public JSONObject addFieldListAndFormula(ApiProcessingStep apiProcessingStep,JSONObject jsonObject) {
        return null;
    }
}
