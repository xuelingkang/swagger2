package com.xzixi.swagger2.plus.demo.controller;

import com.xzixi.swagger2.plus.demo.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "demo", produces = "application/json; charset=UTF-8")
@Api(tags = "用户相关api")
public class UserController {

    @GetMapping
    @ApiOperation(value = "测试")
    public String test(User user) {
        System.out.println(user);
        return "随便返回点什么";
    }

}
