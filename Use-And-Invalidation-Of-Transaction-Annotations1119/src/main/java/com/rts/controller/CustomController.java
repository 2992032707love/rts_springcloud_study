package com.rts.controller;

import com.rts.common.ResultJson;
import com.rts.entity.Custom;
import com.rts.service.CustomService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 买家用户表 前端控制器
 * </p>
 *
 * @author rts
 * @since 2024-06-22 13:53:26
 */
@RestController
@RequestMapping("/custom")
public class CustomController {

    @Resource
    private CustomService customService;

    @PostMapping("/add")
    public ResultJson<Boolean> add(@RequestBody Custom custom) {

        return ResultJson.success(customService.save(custom));
    }

    @GetMapping("/get")
    public ResultJson<List<Custom>> get() {
        return ResultJson.success(customService.getAll());
    }

    @PostMapping("/update/{id}/{username}/{password}/{sex}")
    public ResultJson<Boolean> update(@PathVariable("id") Integer id, @PathVariable("username") String username, @PathVariable("password") String password, @PathVariable("sex") Integer sex) {
        return ResultJson.success(customService.updateById(new Custom(id,username,password,sex)));
    }

    @PostMapping("/delete/{id}")
    public ResultJson<Boolean> delete(@PathVariable("id") Integer id) {
        return ResultJson.success(customService.removeById(id));
    }
}
