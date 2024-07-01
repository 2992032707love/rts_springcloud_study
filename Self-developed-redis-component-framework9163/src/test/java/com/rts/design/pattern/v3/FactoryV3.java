package com.rts.design.pattern.v3;

import com.rts.design.pattern.v2.StrategyHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: RTS
 * @CreateDateTime: 2024/6/6 16:46
 **/
public class FactoryV3 {
    private static Map<String,AbstractZYHandler> abstractZYHandlerMap = new ConcurrentHashMap<>();

    public static AbstractZYHandler get(String parameter) {
        return abstractZYHandlerMap.get(parameter);
    }

    public static void register(String key, AbstractZYHandler abstractZYHandler) {
        System.out.println("key: " + key + "\t" + " abstractZYHandler: " + abstractZYHandler);
        if (null == key && null == abstractZYHandler) {
            return;
        }
        abstractZYHandlerMap.put(key,abstractZYHandler);
    }
}
