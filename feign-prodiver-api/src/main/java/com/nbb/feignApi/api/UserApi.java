package com.nbb.feignApi.api;

import org.springframework.web.bind.annotation.RequestMapping;

public interface UserApi {


    @RequestMapping("/info")
    Object getUserInfo();
}
