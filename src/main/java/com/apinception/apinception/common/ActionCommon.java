package com.apinception.apinception.common;

import java.math.BigDecimal;

public class ActionCommon {

    // action 操作type
    public static final String ADD_LIST = "ADD_LIST";
    public static final String ADD_FIELD = "ADD_FIELD";
    public static final String VALIDATION = "VALIDATE";

    // method type
    public static final String FORMULA = "CALCULATE";
    public static final String QUERY_DB = "QUERY_DB";
    public static final String QUOTATION = "SEQUENCE";

    // apiKey
    public static final String APIID = "apiId";
    public static final String PARAM = "param";

    public static final String LEVEL_1 = "LEVEL_1";
    public static final String LEVEL_2 = "LEVEL_2";

    public static String multiply(String src, String dest) {
        BigDecimal srcDeci = new BigDecimal(src);
        BigDecimal destDeci = new BigDecimal(dest);
        return srcDeci.multiply(destDeci).toString();
    }

}
