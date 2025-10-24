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
        // 服务类商品也许没有库存，可能需要 serviceDuration
        if (req.serviceDuration() == null || req.serviceDuration().isBlank()) {
            throw new IllegalArgumentException("SERVICE product requires serviceDuration (e.g. 'P30D')");
        }
        // 也可能规定 price 可以为0（比如免费试用服务）——取决于业务
    }
}
