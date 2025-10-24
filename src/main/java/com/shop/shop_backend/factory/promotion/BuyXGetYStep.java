package com.shop.shop_backend.factory.promotion;

import com.shop.shop_backend.domain.promotion.PromotionContext;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

@Component
public class BuyXGetYStep implements PromotionStep {
    @Override
    public String code() { return "BUY_X_GET_Y"; }

    @Override
    public int order() { return 30; }

    @Override
    public void apply(PromotionContext ctx, Map<String,Object> cfg) {
        int buy  = Integer.parseInt(cfg.getOrDefault("buy","0").toString());
        int free = Integer.parseInt(cfg.getOrDefault("free","0").toString());
        BigDecimal unit = new BigDecimal(cfg.getOrDefault("unitPrice","0").toString());
        int bundles = Integer.parseInt(cfg.getOrDefault("bundles","1").toString());

        if (buy>0 && free>0 && unit.compareTo(BigDecimal.ZERO)>0) {
            BigDecimal discount = unit
                    .multiply(new BigDecimal(free))
                    .multiply(new BigDecimal(bundles));
            ctx.addDiscount(discount, code());
        }
    }
}
