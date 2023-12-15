package com.nbb.feign.controller;

import cn.hutool.core.map.MapUtil;
import com.nbb.feignApi.api.TestApi;
import com.nbb.feignApi.domain.ParamDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController implements TestApi {
    @Override
    public Object requestBodyTest(Map<String, Object> params) {
        return params;
    }

    @Override
    public Object getMappingParamTest(ParamDTO params) {
        return params;
    }

    @Override
    public Object pathVariableTest(String id) {
        return MapUtil.of("id", id);
    }
}
