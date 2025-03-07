package com.nbb.feign.providerapi.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "feign-provider", path = "/user", contextId = "UserApi")
public interface UserApi {


    @RequestMapping("/info")
    Object getUserInfo();
}
