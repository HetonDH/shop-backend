package com.shop.shop_backend.factory.producttype;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ProductTypeFactory {
    private final Map<String, ProductTypeHandler> registry = new HashMap<>();

    public ProductTypeFactory(List<ProductTypeHandler> handlers) {
        for (ProductTypeHandler h : handlers) {
            registry.put(h.type().toUpperCase(Locale.ROOT), h);
        }
    }

    public ProductTypeHandler of(String type) {
        String key = (type == null ? "SIMPLE" : type.toUpperCase(Locale.ROOT));
        ProductTypeHandler h = registry.get(key);
        if (h == null) throw new IllegalArgumentException("Unsupported product type: " + key);
        return h;
    }
}
