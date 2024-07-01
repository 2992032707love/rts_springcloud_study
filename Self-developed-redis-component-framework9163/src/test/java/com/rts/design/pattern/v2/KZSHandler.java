package com.rts.design.pattern.v2;

import org.springframework.stereotype.Component;

/**
 * @Author: RTS
 * @CreateDateTime: 2024/6/6 15:42
 **/
@Component
public class KZSHandler implements StrategyHandler{
    @Override
    public void getName(String parameter) {
        System.out.println("这里是狂战士策略!" + parameter);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Factory.register("狂战士",this);
    }
}
