package com.nbb.feign.controller;

import cn.hutool.core.map.MapUtil;
import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.nbb.feignApi.api.TestApi;
import com.nbb.feignApi.domain.ParamDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController implements TestApi {

    @Autowired
    private NacosDiscoveryProperties nacosDiscoveryProperties;
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
        HashMap<String, String> result = MapUtil.of("id", id);
        result.put("clusterName", nacosDiscoveryProperties.getClusterName());
        return result;
    }
}
