package com.jt.product.catalog.service.config;
import com.jt.product.catalog.service.domain.Product;
import com.jt.product.catalog.service.respostory.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by Jason Tao on 8/9/2020
 */
@Component
public class DefaultDataLoader implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(DefaultDataLoader.class);

    private final ProductRepository productRepository;

    public DefaultDataLoader(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        logger.info("Initialize the database with default products.");

        Product product1 = Product.builder()
                                    .id(1L)
                                    .name("Lipton Organic Tea Bags")
                                    .description("Lipton Organic Black Tea Bags with a rich taste, intense color and invigorating aroma. 5.9oz, 72 Count Pack of 5.")
                                    .code("PP-GRCE9341")
                                    .price(16.18)
                                    .inStock(false).build();

        this.productRepository.save(product1);

        Product product2 = Product.builder()
                .id(2L)
                .name("Microservices Security in Action")
                .description("Microservices Security in Action teaches you how to address microservices-specific security challenges throughout the system.")
                .code("1617295957")
                .price(59.99)
                .inStock(false).build();

        this.productRepository.save(product2);

        Product product3 = Product.builder()
                .id(3L)
                .name("Portable Charger Anker PowerCore 20100mAh")
                .description("Ultra High Capacity Power Bank with 4.8A Output and PowerIQ Technology, External Battery Pack for iPhone, iPad & Samsung Galaxy & More (Black).")
                .code("A1271")
                .price(32.99)
                .inStock(false).build();

        this.productRepository.save(product3);
    }
}
