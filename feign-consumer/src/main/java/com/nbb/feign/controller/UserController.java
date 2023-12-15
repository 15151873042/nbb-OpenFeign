package com.nbb.feign.controller;

import com.nbb.feign.client.TestClient;
import com.nbb.feign.client.UserClient;
import com.nbb.feignApi.domain.ParamDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserClient userClient;

    @RequestMapping("/info")
    public Object userInfo() {
        Object result = userClient.getUserInfo();
        return result;
    }
}
