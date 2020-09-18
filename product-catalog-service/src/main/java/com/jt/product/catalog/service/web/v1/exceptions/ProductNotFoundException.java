package com.jt.product.catalog.service.web.v1.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Jason Tao on 8/9/2020
 */
@Getter
@Setter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException {

    private String message;

    public ProductNotFoundException(String message) {
        this.message = message;
    }
}
