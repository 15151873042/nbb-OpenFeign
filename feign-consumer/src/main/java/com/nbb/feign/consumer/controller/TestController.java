package com.nbb.feign.consumer.controller;

import com.nbb.feign.providerapi.api.TestApi;
import com.nbb.feign.providerapi.domain.ParamDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private TestApi testApi;

    @RequestMapping("/@RequestBody")
    public Object requestBodyTest(@RequestBody Map<String, Object> params) {
        Object result = testApi.requestBodyTest(params);
        return result;
    }

    @GetMapping("/getMappingParam")
    public Object getMappingParamTest(ParamDTO params) { // get params传参不能直接通过map接收，参数会赋值不进去
        Object result = testApi.getMappingParamTest(params);
        return result;
    }

    @RequestMapping("/@PathVariable/{id}")
    public Object pathVariableTest(@PathVariable("id") String id) {
        Object result = testApi.pathVariableTest(id);
        return result;
    }
}
