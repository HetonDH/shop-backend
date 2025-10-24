package com.shop.shop_backend.service;

import com.shop.shop_backend.domain.promotion.PromotionContext;
import com.shop.shop_backend.factory.promotion.PromotionFactory;
import com.shop.shop_backend.factory.promotion.PromotionStep;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class PricingService {

    private final PromotionFactory promotionFactory;

    public PricingService(PromotionFactory promotionFactory) {
        this.promotionFactory = promotionFactory;
    }

    /**
     * subtotal: 购物车商品小计
     * rules: 促销规则配置列表，ex:
     * [
     *   {"code":"FULL_REDUCTION","threshold":200,"minus":30},
     *   {"code":"PERCENT_OFF","percent":10}
     * ]
     */
    public PromotionContext quote(BigDecimal subtotal, List<Map<String,Object>> rules){
        PromotionContext ctx = new PromotionContext(subtotal);

        List<PromotionStep> chain = promotionFactory.buildChain(rules);

        for (int i = 0; i < chain.size(); i++) {
            PromotionStep step = chain.get(i);
            Map<String,Object> cfg = rules.get(i); // if in the same order
            step.apply(ctx, cfg);
        }

        return ctx;
    }
}
