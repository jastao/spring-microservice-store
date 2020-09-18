package com.jt.inventory.service.web.v1.resources;

import com.jt.inventory.service.domain.InventoryProductItem;
import com.jt.inventory.service.repository.InventoryProductItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * Created by Jason Tao on 8/10/2020
 */
@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryProductItemResource {

    private Logger logger = LoggerFactory.getLogger(InventoryProductItemResource.class);

    private final InventoryProductItemRepository inventoryRepository;

    public InventoryProductItemResource(InventoryProductItemRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @GetMapping("/{product_code}")
    public ResponseEntity<InventoryProductItem> getInventoryByProductCode(@PathVariable("product_code") String productCode) {

        if(productCode == null || productCode.equals("")) {
            return ResponseEntity.badRequest().build();
        }
        logger.info("Find inventory product item with product code [" + productCode + "]");
        Optional<InventoryProductItem> inventoryItemOptional = this.inventoryRepository.findByProductCode(productCode);

        if(inventoryItemOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(inventoryItemOptional.get());
    }

    @GetMapping
    public List<InventoryProductItem> getInventory() {

        logger.info("Find all products from inventory.");
        return this.inventoryRepository.findAll();
    }
}
