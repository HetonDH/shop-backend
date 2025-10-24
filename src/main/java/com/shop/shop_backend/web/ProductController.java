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

    // Creating product（unpublished [default]）
    @PostMapping
    public ProductDtos.Resp create(@Valid @RequestBody ProductDtos.CreateReq req){
        return svc.create(req);
    }

    // publish product
    @PostMapping("/{id}/publish")
    public ProductDtos.Resp publish(@PathVariable Long id){
        return svc.publish(id);
    }

    // unpublish product
    @PostMapping("/{id}/unpublish")
    public ProductDtos.Resp unpublish(@PathVariable Long id){
        return svc.unpublish(id);
    }

    // get single product
    @GetMapping("/{id}")
    public ProductDtos.Resp get(@PathVariable Long id){
        return svc.get(id);
    }
}
