package com.rts.controller;

import com.alibaba.fastjson2.JSONObject;
import com.rts.entity.TPay;
import com.rts.entity.tabulation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@Slf4j
@RequestMapping("/tabu")
public class TabulationController {
    @PostMapping("/add")
    public void judgment(@RequestBody tabulation[] tabulations){
        System.out.println(tabulations.length);
        quickSort(tabulations,0,tabulations.length - 1);
        Integer ro;
        Map<String, String> map = new HashMap<>();
        List<Map<String,String>> list = new ArrayList<>();
        for (int i = 0; i < tabulations.length; i++) {
            ro = tabulations[i].getRow();
            map.put(tabulations[i].getHead(),tabulations[i].getText());
            if ((i < tabulations.length - 1 && ro != tabulations[i+1].getRow() )|| i + 1 == tabulations.length) {
                list.add(map);
                map = new HashMap<>();
                ro = null;
            }
        }
    }

    public void quickSort(tabulation[] arr, int low, int high) {
        int i,j;
        tabulation t ,temp;
        if (low > high) {
            return;
        }
        i = low;
        j = high;

        temp = arr[low];

        while (i < j) {
            while (temp.getRow() <= arr[j].getRow() && i < j) {
                j--;
            }
            while (temp.getRow() >= arr[i].getRow() && i < j) {
                i++;
            }

            if (i < j) {
                t = arr[j];
                arr[j] = arr[i];
                arr[i] = t;
            }
        }
        arr[low] = arr[i];
        arr[i] = temp;

        quickSort(arr,low,j-1);
        quickSort(arr,j+1,high);
    }
}
