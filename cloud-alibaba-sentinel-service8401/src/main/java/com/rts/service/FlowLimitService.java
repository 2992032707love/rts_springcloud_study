package com.rts.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: RTS
 * @CreateDateTime: 2024/6/24 23:54
 **/
@Service
public class FlowLimitService {
    @SentinelResource(value = "common")
    public void common() {
        System.out.println("-----------FlowLimitService come in");
    }

    public static void main(String[] args) {
        Integer[] integers = {1,2,3,4};

        List<Integer> list = new ArrayList<>(Arrays.asList(integers));

        System.out.println(list);
        Integer[] array = list.toArray(new Integer[0]);
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }

    }
}
