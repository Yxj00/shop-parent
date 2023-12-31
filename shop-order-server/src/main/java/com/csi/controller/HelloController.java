package com.csi.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope  //不重启动态刷新
public class HelloController {
    @Value("${appConfig.name}")
    private String appConfigName;
    @Value("${redis.port}")
    private String port;
    @RequestMapping("/hello")
    public String hello(){
        return "参数的内容:"+appConfigName+",redis端口："+ port;
    }
}
