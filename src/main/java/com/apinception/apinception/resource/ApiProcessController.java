package com.apinception.apinception.resource;

import com.apinception.apinception.common.ResultBase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.RestController;

/**
 * 负责面向实际业务逻辑端，处理用户配置好的结构体具体的执行逻辑
 */
@RestController
public class ApiProcessController {


    public ResultBase<Object> process(String json){
        try {
            ObjectMapper mapper = new ObjectMapper();
            String result = mapper.writeValueAsString(json);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
