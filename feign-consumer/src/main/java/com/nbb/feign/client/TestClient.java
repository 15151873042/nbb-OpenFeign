package com.nbb.feign.client;

import com.nbb.feign.fallback.TestClientFallback;
import com.nbb.feignApi.api.TestApi;
import com.nbb.feignApi.domain.ParamDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

/**
 * path = "/test"：由于现在@RequestMapping和@FeignClient不能同时作用与类上，相当于类上加了@RequestMapping("/test")
 */
@FeignClient(value = "feign-provider", path = "/test", contextId = "TestClient", fallback = TestClientFallback.class)
public interface TestClient extends TestApi {

    /**
     * @SpringQueryMap 解决get请求参数无法传递问题
     * https://docs.spring.io/spring-cloud-openfeign/docs/3.1.8/reference/html/#feign-querymap-support
     */
    @Override
    @GetMapping("/getMappingParam")
    Object getMappingParamTest(@SpringQueryMap ParamDTO params);

}
