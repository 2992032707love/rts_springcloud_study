package com.rts.design.pattern.v1;

/**
 * @Author: RTS
 * @CreateDateTime: 2024/6/6 15:26
 **/
public class KZSHandler implements Strategy{
    @Override
    public void getName(String parameter) {
        System.out.println("这里是狂战士!" + parameter);
    }
}
