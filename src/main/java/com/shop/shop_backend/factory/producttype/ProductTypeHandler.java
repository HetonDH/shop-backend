package com.shop.shop_backend.factory.producttype;

import com.shop.shop_backend.domain.Product;
import com.shop.shop_backend.web.dto.ProductDtos;

public interface ProductTypeHandler {
    String type();
    void prepare(Product product, ProductDtos.CreateReq req);
}
