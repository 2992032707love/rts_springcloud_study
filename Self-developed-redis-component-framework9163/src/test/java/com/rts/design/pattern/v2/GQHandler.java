package com.rts.design.pattern.v2;

import org.springframework.stereotype.Component;

/**
 * @Author: RTS
 * @CreateDateTime: 2024/6/6 15:42
 **/
@Component
public class GQHandler implements StrategyHandler{
    @Override
    public void getName(String parameter) {
        System.out.println("这里是鬼泣策略！" + parameter);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Factory.register("鬼泣",this);
    }
}
