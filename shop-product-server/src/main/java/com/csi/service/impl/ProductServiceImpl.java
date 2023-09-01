package com.csi.service.impl;

import com.csi.Product;
import com.csi.dao.ProductDao;
import com.csi.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductDao productDao;
    @Override
    public Product findByPid(Long pid) {
        return productDao.findById(pid).get();
    }
}
