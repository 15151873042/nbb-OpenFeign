package com.nbb.feign.providerapi.api;

import com.nbb.feign.providerapi.domain.ParamDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * path = "/test"：由于现在@RequestMapping和@FeignClient不能同时作用与类上，相当于类上加了@RequestMapping("/test")
 */
@FeignClient(value = "feign-provider", path = "/test", contextId = "TestClient")
public interface TestApi {

    @RequestMapping("/@RequestBody")
    Object requestBodyTest(@RequestBody Map<String, Object> params);

    /**
     * @SpringQueryMap 解决get请求参数无法传递问题
     * https://docs.spring.io/spring-cloud-openfeign/docs/3.1.8/reference/html/#feign-querymap-support
     */
    @GetMapping("/getMappingParam")
    Object getMappingParamTest(@SpringQueryMap ParamDTO params);

    @RequestMapping("/@PathVariable/{id}")
    Object pathVariableTest(@PathVariable("id") String id);

}
