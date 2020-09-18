package com.jt.product.catalog.service.web.v1.resources;

import com.jt.product.catalog.service.domain.Product;
import com.jt.product.catalog.service.services.impl.ProductService;
import com.jt.product.catalog.service.web.v1.exceptions.ProductNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Jason Tao on 8/9/2020
 */
@RestController
@RequestMapping("/api/v1")
@Slf4j
public class ProductCatalogResource {

    private Logger logger = LoggerFactory.getLogger(ProductCatalogResource.class);

    private final ProductService productService;

    public ProductCatalogResource(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> findProducts() {

        logger.info("Searching for all products.");
        return this.productService.findAllProducts();
    }

    @GetMapping("/products/available")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> findAvailableProducts() {

        logger.info("Searching for all available products.");
        return this.productService.findAllAvailableProducts();
    }

    @GetMapping("/product/{code}")
    public Product findProductByCode(@Valid @PathVariable("code") String code) {

        logger.info("Finding product by product code: " + code);
        return this.productService.findProductByCode(code)
                                    .orElseThrow(() -> new ProductNotFoundException("No product associated with product code[" + code + "] is found."));
    }
}
