package com.nbb.feign.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

@Component
public class AuthInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        // 模拟从session中获取当前登录用户的satoken
        String saToken = "token-001";
        template.header("satoken", saToken);
    }
}
