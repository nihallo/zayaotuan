package com.apinception.apinception.service;

import com.alibaba.fastjson.JSONObject;
import com.apinception.apinception.model.ApiProcessingStep;
import org.springframework.stereotype.Service;

/**
 * 做数据回填查询
 */
@Service
public interface CommonQueryService {


    /**
     * 处理添加field并且做公式计算的问题
     * @param apiProcessingStep
     * @return
     */
    public JSONObject addFieldAndFormula(ApiProcessingStep apiProcessingStep, JSONObject jsonObject);

    public JSONObject addFieldAndQueryDb(ApiProcessingStep apiProcessingStep, JSONObject jsonObject);

    public JSONObject addFieldListAndQueryDb(ApiProcessingStep apiProcessingStep, JSONObject jsonObject);

    public JSONObject addFieldListAndFormula(ApiProcessingStep apiProcessingStep, JSONObject jsonObject);

}
