package com.shop.shop_backend.factory.promotion;

import com.shop.shop_backend.domain.promotion.PromotionContext;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

@Component
public class PercentOffStep implements PromotionStep {
    @Override
    public String code() { return "PERCENT_OFF"; }

    @Override
    public int order() { return 20; }

    @Override
    public void apply(PromotionContext ctx, Map<String,Object> cfg) {
        BigDecimal percent = new BigDecimal(cfg.getOrDefault("percent","0").toString());
        if (percent.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal d = ctx.getSubtotal()
                    .multiply(percent)
                    .divide(new BigDecimal("100"));
            ctx.addDiscount(d, code());
        }
    }
}
