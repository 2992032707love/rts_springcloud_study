package com.rts.test;

import com.rts.design.pattern.v2.Factory;
import com.rts.design.pattern.v3.AbstractZYHandler;
import com.rts.design.pattern.v3.FactoryV3;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: RTS
 * @CreateDateTime: 2024/6/6 16:07
 **/
@SpringBootTest
public class DesignPatternTest {

    @Test
    public void methodV2 () {
        String parameter = "剑魂";
        Factory.getStrategyHandler(parameter).getName(parameter);
    }

    /**
     * 策略 + 工厂 + 模板方法 + 钩子
     */
    @Test
    public void methodV3 () {
        String parameter = "剑魂";
        AbstractZYHandler abstractZYHandler = FactoryV3.get(parameter);
        abstractZYHandler.getName(parameter);
        System.out.println(abstractZYHandler.JHMethod(parameter));
        System.out.println(abstractZYHandler.invokeCommon());
    }
}
