package com.apinception.apinception.resource;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.apinception.apinception.common.ActionCommon;
import com.apinception.apinception.common.ResultBase;
import com.apinception.apinception.model.ApiProcessing;
import com.apinception.apinception.model.ApiProcessingStep;
import com.apinception.apinception.model.PlanDataModel;
import com.apinception.apinception.repository.ApiProcessingRepository;
import com.apinception.apinception.service.CommonQueryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 负责面向实际业务逻辑端，处理用户配置好的结构体具体的执行逻辑
 */
@RestController
public class ApiProcessResultController {

    @Autowired
    private CommonQueryService commonQueryService;

    @Autowired
    private ApiProcessingRepository apiProcessingRepository;

    @PostMapping("/process")
    public ResultBase<List<HashMap>> process(@RequestBody String json) throws ParseException {
        ResultBase<List> resultBase = new ResultBase<>();
        if (StringUtils.isEmpty(json)){
            resultBase.setSuccess(false);
//            return resultBase;
        }
        String replace = json.replace("\r\n", "");
        String s = "{\"productCode\":123,\"gender\":\"true\",\"fullName\":\"true\",\"dateOfBirth\":\"2000-02-04\",\"membership\":\"true\",\"doesInsuredSmoke\":\"true\",\"apiId\":\"0\"}";
        JSONObject jsonObject = JSON.parseObject(replace);
        // 同时判别apicode和具体的入参信息是否存在
        if (jsonObject.getString(ActionCommon.APIID)!= null){
            // 获取apiId信息
            String apiId = jsonObject.get(ActionCommon.APIID).toString();

            ApiProcessing apiProcessing = apiProcessingRepository.findAllByApiIdEquals(apiId);
            List<ApiProcessingStep> apiProcessingStepList = apiProcessing.getApiProcessingStepList();
            if (CollectionUtils.isEmpty(apiProcessingStepList)){
                resultBase.setSuccess(false);
//                return resultBase;
            }
            List<ApiProcessingStep> sorted = apiProcessingStepList.stream().sorted(Comparator.comparing(ApiProcessingStep::getStepNumber)).collect(Collectors.toList());
            for (ApiProcessingStep apiProcessingStep : sorted){
                if (apiProcessingStep.getActionType().equals(ActionCommon.ADD_FIELD)){
                       // 基于公式做计算和插入操作
                       if (apiProcessingStep.getMethod().equals(ActionCommon.FORMULA)){
                           jsonObject = commonQueryService.addFieldAndFormula(apiProcessingStep, jsonObject);
                       }
                       if (apiProcessingStep.getMethod().equals(ActionCommon.QUOTATION)){
                           jsonObject = commonQueryService.addFieldAndSequence(apiProcessingStep,jsonObject);
                       }
                }

                if (apiProcessingStep.getActionType().equals(ActionCommon.ADD_LIST)){
                    if (apiProcessingStep.getMethod().equals(ActionCommon.QUERY_DB)){
                           jsonObject = commonQueryService.addFieldListAndQueryDb(apiProcessingStep,jsonObject);
                    }
                }

                if (apiProcessingStep.getActionType().equals(ActionCommon.VALIDATION)){
                    // 基于公式做校验
                       if (apiProcessingStep.getMethod().equals(ActionCommon.FORMULA)){
                           jsonObject = commonQueryService.addValidateAndFormula(apiProcessingStep,jsonObject);
                       }
                }

            }
        }

//        resultBase.setData(jsonObject);
        resultBase.setSuccess(true);// 成功，撒花~~~
        PlanDataModel p = new PlanDataModel();
        p.setAlb("20");
        p.setBenefit("ChildIllness");
        p.setLibility("Death due to infectious diseases");
        p.setPlanBaseOnPrem("1DOLLAR_PER_MONTH");
        p.setPremBreakdown("0.5");
        p.setSA("9,860");
//        List<PlanDataModel> lll = new ArrayList<PlanDataModel>();
//        lll.add(p);
        HashMap hm = new HashMap();
        hm.put("dateOfBirth","2020-01-01");
        hm.put("gender","");
        hm.put("doesInsuredSmoke","");
        hm.put("membership","");
        hm.put("fullName","");
        List<HashMap> lll = new ArrayList<>();
        lll.add(hm);
//        return resultBase(lll);
        ResultBase<List<HashMap>> resultBasea = new ResultBase<>(lll);
        return resultBasea;
//        ResultBase<List<?>> resultBasea = new ResultBase(jsonObject.getJSONObject("PlanDetailList"));
//        return resultBasea;?
    }
}
