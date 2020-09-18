package com.jt.product.catalog.service.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Created by Jason Tao on 8/9/2020
 */
@Entity
@Table(name = "product")
@Setter
@Getter
public class Product {

    @Id
    @NotNull
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "product_name", nullable = false)
    private String name;

    private String description;

    @NotNull
    @Column(name = "product_code", nullable = false, unique = true)
    private String code;

    @NotNull
    private double price;

    @NotNull
    @Transient
    private boolean inStock;

    public Product() {}

    @Builder
    public Product(Long id, String name, String description, String code, double price, boolean inStock) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.code = code;
        this.price = price;
        this.inStock = inStock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id.equals(product.id) && code.equals(product.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code);
    }
}

