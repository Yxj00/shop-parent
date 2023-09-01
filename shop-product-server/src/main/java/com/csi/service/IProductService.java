package com.csi.service;

import com.csi.Product;

public interface IProductService {
    Product findByPid(Long pid);
}
