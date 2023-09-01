package com.csi.feign;

import com.csi.Product;
import com.csi.feign.fallback.ProductFeignFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

//远程调用服务名称
@FeignClient(name = "product-service",fallback = ProductFeignFallBack.class)
public interface ProductFeignApi {
//    创建代理类
    @RequestMapping("/product/{pid}")
    Product findByPid(@PathVariable("pid") Long pid);
}
