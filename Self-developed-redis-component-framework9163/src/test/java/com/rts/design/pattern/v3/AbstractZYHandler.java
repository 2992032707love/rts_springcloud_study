package com.rts.design.pattern.v3;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @Author: RTS
 * @CreateDateTime: 2024/6/6 16:24
 **/

public abstract class AbstractZYHandler implements InitializingBean {

    public void getName (String parameter) {
        throw new UnsupportedOperationException();
    }

    public String JHMethod(String name) {
        throw new UnsupportedOperationException();
    }
    public String GQMethod(String name) {
        throw new UnsupportedOperationException();
    }
    public String KZSMethod(String name) {
        throw new UnsupportedOperationException();
    }

    protected void initResource() {
        System.out.println("init抽象类父类已经统一实现，给大家方便，子类也可以覆写！");
    }

    public abstract String invokeCommon();
}
