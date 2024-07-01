package com.rts.design.pattern.v1;

/**
 * @Author: RTS
 * @CreateDateTime: 2024/6/6 15:24
 **/
public class GQHandler implements Strategy{
    @Override
    public void getName(String parameter) {
        System.out.println("这里是鬼泣！" + parameter);
    }
}
