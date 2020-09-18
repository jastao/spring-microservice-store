package com.jt.inventory.service.domain;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

/**
 * Created by Jason Tao on 8/9/2020
 */
@Entity
@Table(name = "inventory")
@Getter
@Setter
public class InventoryProductItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    @Column(name = "product_code", nullable = false, unique = true)
    private String productCode;

    @NotNull
    @Min(0)
    @Column(name = "product_quantity", nullable = false)
    private Integer productQuantity = 0;

    public InventoryProductItem() {}

    @Builder
    public InventoryProductItem(Long id, String produceCode, Integer productQuantity) {
        this.id = id;
        this.productCode = produceCode;
        this.productQuantity = productQuantity;
    }
}
