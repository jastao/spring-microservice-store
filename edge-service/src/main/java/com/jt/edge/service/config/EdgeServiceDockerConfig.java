package com.jt.edge.service.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by Jason Tao on 8/22/2020
 */
@Profile("docker")
@Configuration
public class EdgeServiceDockerConfig {

    private static final String PRODUCT_CATALOG_SERVICE = "PRODUCT-CATALOG-SERVICE";
    private static final String INVENTORY_SERVICE       = "INVENTORY-SERVICE";
    private static final String ROOT_URI_PREFIX         = "/api";
    private static final String APP_VERSION             = "/v1";
    private static final String URI_PREFIX              = ROOT_URI_PREFIX + APP_VERSION;

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder routeLocatorBuilder) {

        return routeLocatorBuilder.routes()
                    .route(r -> r.path(URI_PREFIX + "/products/**")
                            .uri("lb://" + PRODUCT_CATALOG_SERVICE)
                            .id(PRODUCT_CATALOG_SERVICE))
                    .route(r -> r.path(URI_PREFIX + "/product/**")
                            .uri("lb://" + PRODUCT_CATALOG_SERVICE)
                            .id(PRODUCT_CATALOG_SERVICE))
                    .route(r -> r.path(URI_PREFIX + "/inventory/**")
                            .uri("lb://" + INVENTORY_SERVICE)
                            .id(INVENTORY_SERVICE))
                    .build();
    }
}
