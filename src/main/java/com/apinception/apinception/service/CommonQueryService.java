package com.apinception.apinception.service;

import com.alibaba.fastjson.JSONObject;
import com.apinception.apinception.model.ApiProcessingStep;
import org.springframework.stereotype.Service;

import java.text.ParseException;

/**
 * 做数据回填查询
 */

public interface CommonQueryService {


    /**
     * 处理添加field并且做公式计算的问题
     * @param apiProcessingStep
     * @return
     */
    public JSONObject addFieldAndFormula(ApiProcessingStep apiProcessingStep, JSONObject jsonObject) throws ParseException;

    public JSONObject addFieldAndSequence(ApiProcessingStep apiProcessingStep, JSONObject jsonObject);

    public JSONObject addFieldListAndQueryDb(ApiProcessingStep apiProcessingStep, JSONObject jsonObject);

    public JSONObject addValidateAndFormula(ApiProcessingStep apiProcessingStep, JSONObject jsonObject);



}
