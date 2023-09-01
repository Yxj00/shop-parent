package com.csi.feign.fallback;

import com.csi.Product;
import com.csi.feign.ProductFeignApi;
import org.springframework.stereotype.Component;

@Component
public class ProductFeignFallBack implements ProductFeignApi {
    @Override
    public Product findByPid(Long pid) {
        System.out.println("返回兜底数据");
        return new Product();
    }
}
