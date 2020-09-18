package com.jt.edge.service.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by Jason Tao on 9/7/2020
 */
@Profile("default")
@Configuration
public class EdgeServiceLocalConfig {

    private static final Logger logger = LoggerFactory.getLogger(EdgeServiceDockerConfig.class);

    private static final String DEFAULT_URL     = "localhost";
    private static final String ROOT_URI_PREFIX = "/api";
    private static final String APP_VERSION     = "/v1";
    private static final String URI_PREFIX      = ROOT_URI_PREFIX + APP_VERSION;

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder routeLocatorBuilder) {

        logger.info("Edge service root url: " + DEFAULT_URL);
        return routeLocatorBuilder.routes()
                .route(r -> r.path(URI_PREFIX + "/products/**")
                        .uri(DEFAULT_URL + ":8081")
                        .id("product-catalog-service"))
                .route(r -> r.path(URI_PREFIX + "/product/**")
                        .uri(DEFAULT_URL + ":8081")
                        .id("product-catalog-service"))
                .route(r -> r.path(URI_PREFIX + "/inventory/**")
                        .uri(DEFAULT_URL + ":8082")
                        .id("inventory-service"))
                .build();
    }
}
