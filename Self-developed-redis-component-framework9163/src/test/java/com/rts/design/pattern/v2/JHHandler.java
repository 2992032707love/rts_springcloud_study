package com.rts.design.pattern.v2;

import org.springframework.stereotype.Component;

/**
 * @Author: RTS
 * @CreateDateTime: 2024/6/6 15:36
 **/
@Component
public class JHHandler implements StrategyHandler{
    @Override
    public void getName(String parameter) {
        System.out.println("这里是剑魂！" + parameter);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Factory.register("剑魂",this);
    }
}
