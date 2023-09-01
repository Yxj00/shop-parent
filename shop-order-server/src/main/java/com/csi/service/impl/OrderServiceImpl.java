package com.csi.service.impl;

import com.alibaba.fastjson.JSON;
import com.csi.Product;
import com.csi.dao.OrderDao;
import com.csi.domain.Order;
import com.csi.feign.ProductFeignApi;
import com.csi.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ProductFeignApi productFeignApi;

    @Override
    public Order createOrder(Long productId, Long userId) {
        //远程调⽤商品微服务,查询商品信息
        Product product =productFeignApi.findByPid(productId);// 代理类
        log.info("字节码:{}",productFeignApi.getClass());
        log.info("查询到{}号商品的信息,内容是:{}", productId,
                JSON.toJSONString(product));
        //创建订单并保存
        Order order = new Order();
        order.setUid(userId);
        order.setUsername("玄机教育");
        order.setPid(productId);
        order.setPname(product.getPname());
        order.setPprice(product.getPprice());
        order.setNumber(1);
        orderDao.save(order);
        log.info("创建订单成功,订单信息为{}", JSON.toJSONString(order));
        return order;
    }
}
