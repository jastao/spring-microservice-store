package com.jt.inventory.service.repository;

import com.jt.inventory.service.domain.InventoryProductItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by Jason Tao on 8/9/2020
 */
@Repository
public interface InventoryProductItemRepository extends JpaRepository<InventoryProductItem, Long> {

    Optional<InventoryProductItem> findByProductCode(String code);
}
