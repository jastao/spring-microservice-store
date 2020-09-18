package com.jt.product.catalog.service.services;

import com.jt.product.catalog.service.domain.Product;

import java.util.List;
import java.util.Optional;

/**
 * Created by Jason Tao on 8/9/2020
 */
public interface IProductService {

    List<Product> findAllProducts();

    List<Product> findAllAvailableProducts();

    Optional<Product> findProductByCode(String code);
}
