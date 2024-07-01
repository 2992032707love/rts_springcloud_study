package com.rts.design.pattern;

import org.junit.jupiter.api.Test;

/**
 * @Author: RTS
 * @CreateDateTime: 2024/6/6 15:13
 **/
public class NoDesignDemo {
    public static void main(String[] args) {

    }

    @Test
    public static void more_IfElse(String parameter) {
        if ("剑魂".equalsIgnoreCase(parameter)) {
            System.out.println("这里是剑魂");
        } else if ("鬼泣".equalsIgnoreCase(parameter)) {
            System.out.println("这里是鬼泣");
        } else if ("狂战士".equalsIgnoreCase(parameter)) {
            System.out.println("这里是狂战士");
        }
    }
}
