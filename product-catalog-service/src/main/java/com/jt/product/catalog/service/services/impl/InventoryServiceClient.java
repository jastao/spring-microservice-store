package com.jt.product.catalog.service.services.impl;

import com.jt.product.catalog.service.web.v1.models.InventoryProductResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Jason Tao on 8/13/2020
 */
@Service
@Slf4j
public class InventoryServiceClient {

    private Logger logger = LoggerFactory.getLogger(InventoryServiceClient.class);

    private final InventoryServiceFeignClient inventoryServiceFeignClient;

    public InventoryServiceClient(InventoryServiceFeignClient inventoryServiceFeignClient) {
        this.inventoryServiceFeignClient = inventoryServiceFeignClient;
    }

    public List<InventoryProductResponse> getProductFromInventory() {
        logger.info("Retrieving inventory from inventory service feign client.");
        return this.inventoryServiceFeignClient.getInventory();
    }

    public InventoryProductResponse getProductByProductCode(String produceCode) {
        logger.info("Retrieving product quantity from inventory service feign client by product code.");
        return this.inventoryServiceFeignClient.getInventoryByProductCode(produceCode);
    }

}
