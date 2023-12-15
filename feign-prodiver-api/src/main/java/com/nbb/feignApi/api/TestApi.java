package com.nbb.feignApi.api;

import com.nbb.feignApi.domain.ParamDTO;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

public interface TestApi {

    @RequestMapping("/@RequestBody")
    Object requestBodyTest(@RequestBody Map<String, Object> params);

    @GetMapping("/getMappingParam")
    Object getMappingParamTest(ParamDTO params);

    @RequestMapping("/{id}")
    Object pathVariableTest(@PathVariable("id") String id);

}
