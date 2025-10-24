package com.shop.shop_backend.web;

import com.shop.shop_backend.service.ProductService;
import com.shop.shop_backend.web.dto.ProductDtos;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService svc;
    public ProductController(ProductService svc){
        this.svc = svc;
    }

    // 创建商品（未上架）
    @PostMapping
    public ProductDtos.Resp create(@Valid @RequestBody ProductDtos.CreateReq req){
        return svc.create(req);
    }

    // 上架
    @PostMapping("/{id}/publish")
    public ProductDtos.Resp publish(@PathVariable Long id){
        return svc.publish(id);
    }

    // 下架
    @PostMapping("/{id}/unpublish")
    public ProductDtos.Resp unpublish(@PathVariable Long id){
        return svc.unpublish(id);
    }

    // 查询单个商品
    @GetMapping("/{id}")
    public ProductDtos.Resp get(@PathVariable Long id){
        return svc.get(id);
    }
}
