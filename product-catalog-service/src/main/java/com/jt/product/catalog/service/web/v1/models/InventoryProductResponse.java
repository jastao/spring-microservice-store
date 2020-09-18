package com.jt.product.catalog.service.web.v1.models;

import lombok.Builder;
import lombok.Data;

/**
 * Created by Jason Tao on 8/9/2020
 */
@Data
@Builder
public class InventoryProductResponse {

    private String productCode;

    private int productQuantity;

    public InventoryProductResponse(String produceCode, int productQuantity) {
        this.productCode = produceCode;
        this.productQuantity = productQuantity;
    }
}
