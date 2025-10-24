package com.shop.shop_backend.service;

import com.shop.shop_backend.domain.Product;
import com.shop.shop_backend.factory.producttype.ProductTypeFactory;
import com.shop.shop_backend.repository.ProductRepository;
import com.shop.shop_backend.repository.InventoryRepository;
import com.shop.shop_backend.web.dto.ProductDtos;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.shop_backend.domain.Inventory;

import java.util.NoSuchElementException;

@Service
public class ProductService {

    private final ProductRepository products;
    private final InventoryRepository inventory;
    private final ProductTypeFactory productTypeFactory;

    public ProductService(ProductRepository products,
                          InventoryRepository inventory,
                          ProductTypeFactory productTypeFactory) {
        this.products = products;
        this.inventory = inventory;
        this.productTypeFactory = productTypeFactory;
    }

    @Transactional
    @CacheEvict(value = "product:detail", allEntries = true)
    public ProductDtos.Resp create(ProductDtos.CreateReq req){
        // 1. 基础字段
        Product p = new Product();
        p.setTitle(req.title());
        p.setDescription(req.description());
        p.setPrice(req.price());
        p.setSku(req.sku());
        p.setStatus(Product.Status.INACTIVE);

        // 2. 用商品类型工厂做“特有规则”
        productTypeFactory.of(req.type()).prepare(p, req);

        // 3. 保存商品
        Product saved = products.save(p);

        // 4. 建库存（服务类可能不需要库存？简单起见都建）
        int initStock = req.initStock() == null ? 0 : req.initStock();
        inventory.save(new Inventory(saved, Math.max(0, initStock)));

        // 5. 返回 DTO
        return new ProductDtos.Resp(
                saved.getId(),
                saved.getTitle(),
                saved.getDescription(),
                saved.getPrice(),
                saved.getSku(),
                saved.getStatus().name(),
                saved.getType().name()
        );
    }

    @Transactional
    @CacheEvict(value = "product:detail", key = "#id")
    public ProductDtos.Resp publish(Long id){
        Product p = products.findById(id).orElseThrow();
        p.setStatus(Product.Status.ACTIVE);
        return toResp(p);
    }

    @Transactional
    @CacheEvict(value = "product:detail", key = "#id")
    public ProductDtos.Resp unpublish(Long id){
        Product p = products.findById(id).orElseThrow();
        p.setStatus(Product.Status.INACTIVE);
        return toResp(p);
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "product:detail", key = "#id")
    public ProductDtos.Resp get(Long id){
        return products.findById(id).map(this::toResp)
                .orElseThrow(() -> new NoSuchElementException("not found"));
    }

    private ProductDtos.Resp toResp(Product p){
        return new ProductDtos.Resp(
                p.getId(),
                p.getTitle(),
                p.getDescription(),
                p.getPrice(),
                p.getSku(),
                p.getStatus().name(),
                p.getType().name()
        );
    }
}
