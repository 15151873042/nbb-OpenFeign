package com.nbb.feign.provider.controller;

import cn.hutool.core.map.MapUtil;
import com.nbb.feign.providerapi.api.UserApi;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController implements UserApi {
    @Override
    public Object getUserInfo() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String satoken = request.getHeader("satoken");
        System.out.println("从请求header中获取到了satoken=" + satoken);
        return MapUtil.of("userName", "张三");
    }
}
