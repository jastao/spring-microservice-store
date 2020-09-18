package com.jt.product.catalog.service.services.impl;

import com.jt.product.catalog.service.domain.Product;
import com.jt.product.catalog.service.respostory.ProductRepository;
import com.jt.product.catalog.service.services.IProductService;
import com.jt.product.catalog.service.web.v1.models.InventoryProductResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Jason Tao on 8/9/2020
 */
@Service
@Transactional
@Slf4j
public class ProductService implements IProductService {

    private Logger logger = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;
    private final InventoryServiceClient inventoryServiceClient;

    public ProductService(ProductRepository productRepository, InventoryServiceClient inventoryServiceClient) {
        this.productRepository = productRepository;
        this.inventoryServiceClient = inventoryServiceClient;
    }

    @Override
    public List<Product> findAllProducts() {

        // Return the list of products
        logger.info("Find all products.");
        List<Product> allProducts = this.productRepository.findAll();

        final Map<String, Integer> productsMap = getProductsQuantityInStock();

        if(!productsMap.isEmpty()) {
            allProducts.stream()
                    .forEach(product -> product.setInStock(productsMap.get(product.getCode()) > 0));
        }

        return allProducts;
    }

    @Override
    public List<Product> findAllAvailableProducts() {

        // Return the list of available products
        logger.info("Find all available products in inventory.");

        List<Product> allProducts = this.productRepository.findAll();
        final Map<String, Integer> productsMap = getProductsQuantityInStock();

        final List<Product> availableProducts = allProducts.stream()
                        .filter(product -> productsMap.get(product.getCode()) != null &&
                                   productsMap.get(product.getCode()) > 0)
                        .collect(Collectors.toList());

        if(!availableProducts.isEmpty()) {
            availableProducts.stream().forEach(product -> product.setInStock(productsMap.get(product.getCode()) > 0));
            return availableProducts;
        }
        return Collections.emptyList();
    }

    @Override
    public Optional<Product> findProductByCode(String code) {

        Optional<Product> productOptional = this.productRepository.findByCode(code);

        if(productOptional.isPresent()) {
            final InventoryProductResponse productResponse = this.inventoryServiceClient.getProductByProductCode(productOptional.get().getCode());
            if(productResponse != null && "XXX".equalsIgnoreCase(productResponse.getProductCode())) {
                logger.info("Fallback is returned a default product.");
                productOptional.get().setInStock(false);
            }
            productOptional.get().setInStock(productResponse.getProductQuantity() > 0);
        }
        return productOptional;
    }

    private Map<String, Integer> getProductsQuantityInStock() {

        logger.info("Getting a map of products from inventory feign client.");
        final Map<String, Integer> productsMap = new HashMap<>();
        for(InventoryProductResponse product : this.inventoryServiceClient.getProductFromInventory()) {
            productsMap.put(product.getProductCode(), product.getProductQuantity());
        }
        return productsMap;
    }
}
