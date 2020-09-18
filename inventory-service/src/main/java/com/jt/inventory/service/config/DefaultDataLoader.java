package com.jt.inventory.service.config;

import com.jt.inventory.service.domain.InventoryProductItem;
import com.jt.inventory.service.repository.InventoryProductItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Created by Jason Tao on 8/10/2020
 */
@Profile("default")
@Component
public class DefaultDataLoader implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(DefaultDataLoader.class);

    private final InventoryProductItemRepository inventoryRepository;

    public DefaultDataLoader(InventoryProductItemRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        logger.info("Initializing default inventory product items.");

        InventoryProductItem item1 = InventoryProductItem.builder()
                .id(1L)
                .produceCode("PP-GRCE9341")
                .productQuantity(125)
                .build();

        this.inventoryRepository.save(item1);

        InventoryProductItem item2= InventoryProductItem.builder()
                .id(2L)
                .produceCode("1617295957")
                .productQuantity(5)
                .build();

        this.inventoryRepository.save(item2);

        InventoryProductItem item3 = InventoryProductItem.builder()
                .id(3L)
                .produceCode("A1271")
                .productQuantity(0)
                .build();

        this.inventoryRepository.save(item3);
    }
}
