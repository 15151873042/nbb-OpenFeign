package com.nbb.feign.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import com.nbb.feign.client.TestClient;
import com.nbb.feignApi.domain.ParamDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestClient testClient;

    @RequestMapping("/@RequestBody")
    public Object requestBodyTest(@RequestBody Map<String, Object> params) {
        Object result = testClient.requestBodyTest(params);
        return result;
    }

    @GetMapping("/getMappingParam")
    public Object getMappingParamTest(ParamDTO params) { // get params传参不能直接通过map接收，参数会赋值不进去
        Object result = testClient.getMappingParamTest(params);
        return result;
    }

    @RequestMapping("/{id}")
    public Object pathVariableTest(@PathVariable("id") String id) {
        Object result = testClient.pathVariableTest(id);
        return result;
    }
}
