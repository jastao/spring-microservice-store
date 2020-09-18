package com.jt.product.catalog.service.services.impl;

import com.jt.product.catalog.service.config.InventoryServiceFeignConfiguration;
import com.jt.product.catalog.service.web.v1.models.InventoryProductResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Created by Jason Tao on 8/13/2020
 */
@FeignClient(name = InventoryServiceFeignClient.INVENTORY_SERVICE_NAME, configuration = InventoryServiceFeignConfiguration.class)
public interface InventoryServiceFeignClient {

    String INVENTORY_SERVICE_NAME = "inventory-service";

    @CircuitBreaker(name = INVENTORY_SERVICE_NAME)
    @GetMapping("/api/v1/inventory")
    List<InventoryProductResponse> getInventory();

    @CircuitBreaker(name = INVENTORY_SERVICE_NAME)
    @GetMapping("/api/v1/inventory/{produceCode}")
    InventoryProductResponse getInventoryByProductCode(@PathVariable("produceCode") String productCode);

}
