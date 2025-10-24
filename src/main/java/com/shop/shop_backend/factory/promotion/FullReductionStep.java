package com.shop.shop_backend.factory.promotion;

import com.shop.shop_backend.domain.promotion.PromotionContext;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

@Component
public class FullReductionStep implements PromotionStep {
    @Override
    public String code() { return "FULL_REDUCTION"; }

    @Override
    public int order() { return 10; }

    @Override
    public void apply(PromotionContext ctx, Map<String,Object> cfg) {
        BigDecimal threshold = new BigDecimal(cfg.getOrDefault("threshold","0").toString());
        BigDecimal minus     = new BigDecimal(cfg.getOrDefault("minus","0").toString());

        if (ctx.getSubtotal().compareTo(threshold) >= 0) {
            ctx.addDiscount(minus, code());
        }
    }
}
