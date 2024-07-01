package com.rts.design.pattern.v3;

import org.springframework.stereotype.Component;

/**
 * @Author: RTS
 * @CreateDateTime: 2024/6/6 16:40
 **/
@Component
public class JHHandlerV3 extends AbstractZYHandler{
    public void getName (String parameter) {
        System.out.println("这里是剑魂类！" + parameter);
    }

    public String JHMethod(String name) {
        return "我是剑魂特有的方法： "  + name;
    }
    @Override
    public String invokeCommon() {
        System.out.println("我是剑魂，这里是JHHandlerV3统一实现抽象父类的invokeCommon方法");
        return "我是剑魂，这里是JHHandlerV3统一实现抽象父类的invokeCommon方法";
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        FactoryV3.register("剑魂",this);
    }
}
