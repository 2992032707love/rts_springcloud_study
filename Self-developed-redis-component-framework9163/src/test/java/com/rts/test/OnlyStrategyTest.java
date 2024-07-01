package com.rts.test;

import com.rts.design.pattern.v1.GQHandler;
import com.rts.design.pattern.v1.JHHandler;
import com.rts.design.pattern.v1.KZSHandler;
import org.junit.jupiter.api.Test;

/**
 * @Author: RTS
 * @CreateDateTime: 2024/6/6 15:27
 **/
public class OnlyStrategyTest {


    public static void more_IfElse(String parameter) {
        if ("剑魂".equalsIgnoreCase(parameter)) {
            new JHHandler().getName(parameter);
        } else if ("鬼泣".equalsIgnoreCase(parameter)) {
            new GQHandler().getName(parameter);
        } else if ("狂战士".equalsIgnoreCase(parameter)) {
            new KZSHandler().getName(parameter);
        }
    }

    public static void main(String[] args) {
        more_IfElse("鬼泣");
    }
    @Test
    public void test() {
        more_IfElse("剑魂");
    }
}
