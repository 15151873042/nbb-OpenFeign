package com.nbb.feign.consumer.fallback;

import cn.hutool.core.map.MapUtil;
import com.nbb.feign.providerapi.api.TestApi;
import com.nbb.feign.providerapi.domain.ParamDTO;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class TestClientFallback implements TestApi {
    @Override
    public Object requestBodyTest(Map<String, Object> params) {
        return this.commonProcess();
    }

    @Override
    public Object getMappingParamTest(ParamDTO params) {
        return this.commonProcess();
    }

    @Override
    public Object pathVariableTest(String id) {
        return this.commonProcess();
    }

    private Object commonProcess() {
        Map<String, String> fallbackResult = MapUtil.of("Circuit Breaker", "调用出错，断路器介入");
        return fallbackResult;
    }
}
