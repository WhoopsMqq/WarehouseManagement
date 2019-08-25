package com.whoops.pojo;

import java.util.HashMap;
import java.util.Map;

public class Constants {

    /**
     * 配件种类
     */
    public static final int PRODUCTION_ACCESSORIES = 1;//生产配件
    public static final int PACKAGING_ACCESSORIES = 2;//包装配件
    public static final int OTHER_ACCESSORIES = 3;//其他配件
    /**
     * 配件种类Map
     */
    public static final Map<Integer,String> ACCESSORIES_MAP  = new HashMap<>();
    static {
        ACCESSORIES_MAP.put(PRODUCTION_ACCESSORIES,"生产配件");
        ACCESSORIES_MAP.put(PACKAGING_ACCESSORIES,"包装配件");
        ACCESSORIES_MAP.put(OTHER_ACCESSORIES,"其他配件");
    }

}
