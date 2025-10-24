package com.shop.shop_backend.factory.producttype;

import com.shop.shop_backend.domain.Product;
import com.shop.shop_backend.web.dto.ProductDtos;
import org.springframework.stereotype.Component;

@Component
public class ServiceProductHandler implements ProductTypeHandler {
    @Override
    public String type() { return "SERVICE"; }

    @Override
    public void prepare(Product product, ProductDtos.CreateReq req) {
        product.setType(Product.Type.SERVICE);
        // MAY NEED SERVICE DURATION Kind of like G2G
        if (req.serviceDuration() == null || req.serviceDuration().isBlank()) {
            throw new IllegalArgumentException("SERVICE product requires serviceDuration (e.g. 'P30D')");
        }
        // ALSO CAN SET PRICE TO ZERO
    }
}
