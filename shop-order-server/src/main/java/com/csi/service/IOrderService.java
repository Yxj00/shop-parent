package com.csi.service;

import com.csi.domain.Order;

public interface IOrderService {
//    传商品ID
    Order createOrder(Long productId, Long userId);
}
