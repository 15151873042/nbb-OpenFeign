package com.nbb.feign.client;

import com.nbb.feign.fallback.TestClientFallback;
import com.nbb.feignApi.api.TestApi;
import com.nbb.feignApi.api.UserApi;
import com.nbb.feignApi.domain.ParamDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * path = "/test"：由于现在@RequestMapping和@FeignClient不能同时作用与类上，相当于类上加了@RequestMapping("/test")
 */
@FeignClient(value = "feign-provider", path = "/user", contextId = "UserClient")
public interface UserClient extends UserApi {

}
