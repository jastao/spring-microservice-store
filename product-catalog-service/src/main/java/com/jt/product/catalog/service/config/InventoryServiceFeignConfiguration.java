package com.jt.product.catalog.service.config;

import com.jt.product.catalog.service.services.impl.InventoryServiceFeignClient;
import com.jt.product.catalog.service.services.fallback.InventoryServiceFeignClientFallback;
import feign.Feign;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.feign.FeignDecorators;
import io.github.resilience4j.feign.Resilience4jFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

/**
 * Created by Jason Tao on 8/20/2020
 */
@RequiredArgsConstructor
public class InventoryServiceFeignConfiguration {

    private final CircuitBreakerRegistry cbRegistry;

    @Bean
    @Scope("prototype")
    public Feign.Builder feignBuilder() {

        CircuitBreaker cb = cbRegistry.circuitBreaker(InventoryServiceFeignClient.INVENTORY_SERVICE_NAME);

        FeignDecorators decorators = FeignDecorators.builder()
                                            .withCircuitBreaker(cb)
                                            .withFallbackFactory(InventoryServiceFeignClientFallback::new)
                                            .build();
        return Resilience4jFeign.builder(decorators);
    }

}
