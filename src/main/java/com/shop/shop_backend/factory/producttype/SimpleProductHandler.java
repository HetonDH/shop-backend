package com.shop.shop_backend.factory.producttype;

import com.shop.shop_backend.domain.Product;
import com.shop.shop_backend.web.dto.ProductDtos;
import org.springframework.stereotype.Component;

@Component
public class SimpleProductHandler implements ProductTypeHandler {
    @Override
    public String type() { return "SIMPLE"; }

    @Override
    public void prepare(Product product, ProductDtos.CreateReq req) {
        product.setType(Product.Type.SIMPLE);
        // simple product rule(need price)
        if (product.getPrice() == null) {
            throw new IllegalArgumentException("SIMPLE product requires price");
        }
    }
}
