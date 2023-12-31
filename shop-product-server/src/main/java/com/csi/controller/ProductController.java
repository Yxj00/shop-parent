package com.csi.controller;

import com.alibaba.fastjson.JSON;
import com.csi.Product;
import com.csi.service.IProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ProductController {
    @Autowired
    private IProductService productService;
    @Value("${server.port}")
    private String port;
    //商品信息查询
    @RequestMapping("/product/{pid}")
    public Product findByPid(@PathVariable("pid") Long pid) {
        log.info("接下来要进⾏{}号商品信息的查询", pid);
        Product product = productService.findByPid(pid);
        product.setPname(product.getPname()+",data from "+port);
        log.info("商品信息查询成功,内容为{}", JSON.toJSONString(product));
        return product;
    }
}
