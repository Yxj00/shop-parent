package com.csi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableFeignClients //扫描当前包和其子包下贴@FeignClient的注解接口  feign
public class OrderServer {
    public static void main(String[] args) {
        SpringApplication.run(OrderServer.class,args);
    }
//    通过Java代码去调⽤⼀个http的接⼝地址，我们可以使⽤RestTemplate来进⾏调⽤.
//    在OrderServiceImpl中注⼊RestTemplate并实现远程调⽤
    @Bean
    @LoadBalanced  //表示集成Ribbon进行负载均衡
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
