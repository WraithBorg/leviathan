package com.io.hydralisk.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统常量
 */
public class CConstant {
    public static final String IMAGE_HOST = "https://kfbc-deitui-com.oss-cn-hangzhou.aliyuncs.com/";
    public static final String DEFAULT_TELEPHONE = "110";
    public static final String DEFAULT_HEAD_URL = "http://localhost:8888/star/image/";
    public static final String LOCAL_HEAD_PATH = "C:/springboot/upload/";
//    public static final String WEB_HOST = "http://localhost:8080/";
    public static final HashMap PAY_TYPE = new HashMap(){{
        put("alipay", "支付宝");
    }};
}
