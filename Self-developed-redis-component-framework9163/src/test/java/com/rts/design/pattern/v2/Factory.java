package com.rts.design.pattern.v2;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: RTS
 * @CreateDateTime: 2024/6/6 15:32
 **/
public class Factory {
    private static Map<String,StrategyHandler> strategymap = new ConcurrentHashMap<>();

    public static StrategyHandler getStrategyHandler(String parameter) {
        return strategymap.get(parameter);
    }

    public static void register(String key,StrategyHandler strategyHandler) {
        System.out.println("key: " + key + "\t" + " strategyHandler: " + strategyHandler);
        if (null == key && null == strategyHandler) {
            return;
        }
        strategymap.put(key,strategyHandler);
    }

}
