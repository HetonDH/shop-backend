package com.shop.shop_backend.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "inventory")
public class Inventory {

    @Id
    private Long productId;

    @OneToOne(optional = false)
    @MapsId
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = false)
    private Integer stock = 0;

    public Inventory() {}

    public Inventory(Product product, Integer stock) {
        this.product = product;
        this.stock = stock;
    }

    public Long getProductId() { return productId; }
    public Product getProduct() { return product; }
    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }
}
