package com.apinception.apinception.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.apinception.apinception.common.ActionCommon;
import com.apinception.apinception.model.ApiProcessingStep;
import com.apinception.apinception.model.PlanDataModel;
import com.apinception.apinception.repository.ApiPlanDataRepository;
import com.apinception.apinception.service.CommonQueryService;
import com.apinception.apinception.service.FormulaCaculateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static javax.swing.UIManager.put;

@Service
public class CommonQueryServiceImpl implements CommonQueryService {

    @Autowired
    private ApiPlanDataRepository apiPlanDataRepository;

    @Autowired
    private FormulaCaculateService formulaCaculateService;

    // 调用方法getAge
    private static final String GETAGE = "getAge";

    private static final String GETAMOUNT = "getAmount";


    @Override
    public JSONObject addFieldAndFormula(ApiProcessingStep apiProcessingStep,JSONObject jsonObject) throws ParseException {
        // 执行公式
        String formula = apiProcessingStep.getFormula();
        if (StringUtils.isEmpty(formula)){
            return jsonObject;
        }
        // 过滤根据括号过滤得到方法名以及需要传递的参数
        String rex="[()]+";
        String[] str=formula.split(rex);
        if (str != null && str.length == 2){
            // 调用方法
            String methodName = str[0];
            // 方法入参
            String param = str[1].split("\\.")[1];
            // 当调用计算年龄方法时
            if (GETAGE.equals(methodName)){
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//注意月份是MM
                Date date = simpleDateFormat.parse(jsonObject.getString(param));
                int ageLastBirthday = formulaCaculateService.getAgeLastBirthday(date);
                String dataLevel = apiProcessingStep.getDataLevel();
                if (dataLevel.equals(ActionCommon.LEVEL_1)){
                    String fieldNameListName = apiProcessingStep.getFieldNameListName();
                    jsonObject.put(fieldNameListName,ageLastBirthday);
                }
            }else if (GETAMOUNT.equals(methodName)){
                int amount = formulaCaculateService.getAmount();
                String addToWhichListName = apiProcessingStep.getAddToWhichListName();
                JSONObject jsonObject1 = jsonObject.getJSONObject(addToWhichListName);
                jsonObject1.put(apiProcessingStep.getFieldNameListName(), amount);
                jsonObject.put(addToWhichListName,jsonObject1);
            }
            return jsonObject;
        }else {
            return jsonObject;
        }
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

        String whereCluse = apiProcessingStep.getWhereCluse();
        // 基于等号分隔 左边是查询的参数 右边是相对应json中所需要的字段值  example:alb =dataAtLevel0.ageLastBirthday
        String[] split = whereCluse.split("\\=");
        String[] split1 = split[1].split("\\.");
        // 获取对应字段值
        String string = jsonObject.getString(split1[1]);
        List<PlanDataModel> all = apiPlanDataRepository.findAll();
        PlanDataModel allByAlbEquals = apiPlanDataRepository.findByAlbIn(string);
        String fieldNameListName = apiProcessingStep.getFieldNameListName();
        jsonObject.put(fieldNameListName,allByAlbEquals);
        return jsonObject;
    }

    @Override
    public JSONObject addValidateAndFormula(ApiProcessingStep apiProcessingStep, JSONObject jsonObject) {
        // 获取当前表达式
        String formula = apiProcessingStep.getFormula();
        String[] formulaSplit = formula.split("\\.");
        // 获取比较表达式  格式为 ageLastBirthday > 17   0:验证字段 1:比较符号 2:限制参数
        String[] compareSplit = formulaSplit[1].split("(?<=<=)|(?=<=)|(?<=<)(?!=)|(?=<)|(?<=>=)|(?=>=)|(?<=>)(?!=)|(?=>)|(?<===)|(?===)|(?<=!=)|(?=!=)");
        // 获取相应字段值
        String val = jsonObject.getString(compareSplit[0]);
        if (!StringUtils.isEmpty(val)){
            Integer num1 = Integer.valueOf(val);
            Integer num2 = Integer.valueOf(compareSplit[2]);
            int i = num1.compareTo(num2);
            switch(compareSplit[1]){
                case ">":
                    if (i < 1){
                        jsonObject.put(apiProcessingStep.getErrorType(),apiProcessingStep.getErrorMessage());
                    };break;
                case "<":
                    if (i > -1){
                        jsonObject.put(apiProcessingStep.getErrorType(),apiProcessingStep.getErrorMessage());
                    };break;
                case "=":
                    if (i != 0){
                        jsonObject.put(apiProcessingStep.getErrorType(),apiProcessingStep.getErrorMessage());
                    };break;
                default:
                    break;
            }
        }

        return jsonObject;
    }
}
