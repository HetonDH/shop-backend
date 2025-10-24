package com.shop.shop_backend.factory.producttype;

import com.shop.shop_backend.domain.Product;
import com.shop.shop_backend.web.dto.ProductDtos;
import org.springframework.stereotype.Component;

@Component
public class VariantProductHandler implements ProductTypeHandler {
    @Override
    public String type() { return "VARIANT"; }

    @Override
    public void prepare(Product product, ProductDtos.CreateReq req) {
        product.setType(Product.Type.VARIANT);
        if (req.variantKey() == null || req.variantKey().isBlank()) {
            throw new IllegalArgumentException("VARIANT product requires variantKey like 'color:size'");
        }
        // Save Variant key to some type of form?
    }
}
