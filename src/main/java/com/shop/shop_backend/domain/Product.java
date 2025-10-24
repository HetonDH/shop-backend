package com.shop.shop_backend.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity //Tell JPA the products form, define the product type, status, upgrade the time stamp
@Table(name = "products")
public class Product {

    public enum Status { ACTIVE, INACTIVE }
    public enum Type { SIMPLE, VARIANT, SERVICE }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Type type = Type.SIMPLE;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "text")
    private String description;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal price;

    @Column(unique = true)
    private String sku;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.INACTIVE; // 默认未上架

    @Column(nullable = false)
    private Instant createdAt = Instant.now();

    @Column(nullable = false)
    private Instant updatedAt = Instant.now();

    @PreUpdate
    void touch() {
        this.updatedAt = Instant.now();
    }

    // getters / setters
    public Long getId() { return id; }

    public Product.Type getType() { return type; }
    public void setType(Product.Type type) { this.type = type; }

    public String getTitle() { return title; }
    public void setTitle(String t) { this.title = t; }

    public String getDescription() { return description; }
    public void setDescription(String d) { this.description = d; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal p) { this.price = p; }

    public String getSku() { return sku; }
    public void setSku(String sku) { this.sku = sku; }

    public Product.Status getStatus() { return status; }
    public void setStatus(Product.Status s) { this.status = s; }
}
