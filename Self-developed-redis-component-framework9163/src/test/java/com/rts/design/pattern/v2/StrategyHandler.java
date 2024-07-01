package com.rts.design.pattern.v2;

import org.springframework.beans.factory.InitializingBean;

/**
 * @Author: RTS
 * @CreateDateTime: 2024/6/6 15:33
 **/
public interface StrategyHandler extends InitializingBean {
    public void getName(String parameter);
}
