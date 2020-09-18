package com.jt.product.catalog.service.services.fallback;

import com.jt.product.catalog.service.services.impl.InventoryServiceFeignClient;
import com.jt.product.catalog.service.web.v1.models.InventoryProductResponse;
import feign.FeignException;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

/**
 * Created by Jason Tao on 8/18/2020
 */
@Slf4j
public class InventoryServiceFeignClientFallback implements InventoryServiceFeignClient {

    private static final Logger logger = LoggerFactory.getLogger(InventoryServiceFeignClientFallback.class);

    private Exception cause;

    public InventoryServiceFeignClientFallback(Exception cause) {
        this.cause = cause;
    }

    @Override
    public List<InventoryProductResponse> getInventory() {

        if(cause instanceof FeignException) {
            logger.info("Fallback method called due to failed connection the feign client. ");
        } else if (cause instanceof CallNotPermittedException) {
            logger.info("Fallback method called due to circuit breaker in OPEN state.");
        } else {
            logger.info("Fallback method called due to other exception that triggered the circuit breaker.");
        }
        return Collections.emptyList();
    }

    @Override
    public InventoryProductResponse getInventoryByProductCode(String productCode) {

        if(cause instanceof FeignException) {
            logger.info("Fallback method called due to failed connection the feign client. ");
        } else if (cause instanceof CallNotPermittedException) {
            logger.info("Fallback method called due to circuit breaker in OPEN state.");
        } else {
            logger.info("Fallback method called due to other exception that triggered the circuit breaker.");
        }
        return InventoryProductResponse.builder().productCode("XXX").productQuantity(0).build();
    }
}
