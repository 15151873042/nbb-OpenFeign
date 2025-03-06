package com.nbb.feign.consumer.controller;

import com.nbb.feign.providerapi.api.UserApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserApi userApi;

    @RequestMapping("/info")
    public Object userInfo() {
        Object result = userApi.getUserInfo();
        return result;
    }
}
