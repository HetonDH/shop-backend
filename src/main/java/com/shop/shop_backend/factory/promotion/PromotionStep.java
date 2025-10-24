package com.shop.shop_backend.factory.promotion;

import com.shop.shop_backend.domain.promotion.PromotionContext;
import java.util.Map;

public interface PromotionStep {
    String code(); // "FULL_REDUCTION", "PERCENT_OFF", etc.

    void apply(PromotionContext ctx, Map<String,Object> cfg);

    default int order(){ return 100; }
}
