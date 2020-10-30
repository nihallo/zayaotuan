package com.apinception.apinception.resource;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.apinception.apinception.common.ActionCommon;
import com.apinception.apinception.common.ResultBase;
import com.apinception.apinception.model.ApiProcessing;
import com.apinception.apinception.model.ApiProcessingStep;
import com.apinception.apinception.repository.ApiProcessingRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 负责面向实际业务逻辑端，处理用户配置好的结构体具体的执行逻辑
 */
@RestController
public class ApiProcessResultController {


    @Autowired
    private ApiProcessingRepository apiProcessingRepository;

    public ResultBase<Object> process(String json){
        ResultBase<Object> resultBase = new ResultBase<>();
        if (StringUtils.isEmpty(json)){
            resultBase.setSuccess(false);
            return resultBase;
        }

        JSONObject jsonObject = JSON.parseObject(json);
        // 同时判别apicode和具体的入参信息是否存在
        if (jsonObject.containsKey(ActionCommon.APIID) && jsonObject.get(ActionCommon.APIID) != null
                && jsonObject.containsKey(ActionCommon.PARAM) && jsonObject.get(ActionCommon.PARAM) != null){
            // 获取apiId信息
            String apiId = jsonObject.get(ActionCommon.APIID).toString();

            ApiProcessing apiProcessing = apiProcessingRepository.findById(apiId).get();
            List<ApiProcessingStep> apiProcessingStepList = apiProcessing.getApiProcessingStepList();
            if (CollectionUtils.isEmpty(apiProcessingStepList)){
                resultBase.setSuccess(false);
                return resultBase;
            }
            List<ApiProcessingStep> sorted = apiProcessingStepList.stream().sorted(Comparator.comparing(ApiProcessingStep::getStepNumber)).collect(Collectors.toList());
            for (ApiProcessingStep apiProcessingStep : sorted){
                if (apiProcessingStep.getActionType().equals(ActionCommon.ADD_FIELD) && apiProcessingStep.getMethod().equals(ActionCommon.FORMULA)){

                }

                if (apiProcessingStep.getActionType().equals(ActionCommon.ADD_LIST) && apiProcessingStep.getMethod().equals(ActionCommon.FORMULA)){

                }

                if (apiProcessingStep.getActionType().equals(ActionCommon.ADD_FIELD) && apiProcessingStep.getMethod().equals(ActionCommon.QUERY_DB)){

                }

                if (apiProcessingStep.getActionType().equals(ActionCommon.ADD_LIST) && apiProcessingStep.getMethod().equals(ActionCommon.QUERY_DB)){

                }
            }
        }


        return null;
    }
}
