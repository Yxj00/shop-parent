package com.csi.fitler;


import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Component
public class TimeGatewayFilterFactory extends AbstractGatewayFilterFactory<TimeGatewayFilterFactory.Config> {
    private static final String BEGIN_TIME = "beginTime";
    public TimeGatewayFilterFactory(){
        super(Config.class);
    }
    //读取配置文件中的参数 赋值到 配置类中
    //- Time=true,1,hello
    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("show");
//        return Arrays.asList("show","xx","yy");
    }
    //拦截到之后就会调用apply方法，把创建对象时候反射创建出来的Config传入进来
    @Override
    public GatewayFilter apply(Config config) {
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange,
                                     GatewayFilterChain chain) {
                if(!config.show){
                    return chain.filter(exchange);
                }
                exchange.getAttributes().put(BEGIN_TIME,
                        System.currentTimeMillis());
                /**
                 * pre的逻辑
                 * chain.filter().then(Mono.fromRunable(()->{
                 * post的逻辑
                 * }))
                 */
                System.out.println("前置逻辑");
                return chain.filter(exchange).then(Mono.fromRunnable(()->{
                    System.out.println("后置逻辑");
                    Long startTime = exchange.getAttribute(BEGIN_TIME);
                    if (startTime != null) {
                        System.out.println(exchange.getRequest().getURI() +
                                "请求耗时: " + (System.currentTimeMillis() - startTime) + "ms");
                    }
                }));
            }
        };
    }

    @Setter
    @Getter
    static class Config{
        private boolean show;
//        private Long xx;
//        private String yy;
    }
}
