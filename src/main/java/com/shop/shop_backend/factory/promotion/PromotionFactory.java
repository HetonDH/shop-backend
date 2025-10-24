package com.shop.shop_backend.factory.promotion;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PromotionFactory {

    private final Map<String, PromotionStep> registry = new HashMap<>();

    public PromotionFactory(List<PromotionStep> steps){
        for (PromotionStep s : steps) {
            registry.put(s.code(), s);
        }
    }

    public List<PromotionStep> buildChain(List<Map<String,Object>> rules){
        // rules: [{code:"FULL_REDUCTION", threshold:200, minus:30}, ...]
        return rules.stream()
                .map(rule -> registry.get(Objects.toString(rule.get("code"), "")))
                .filter(Objects::nonNull)
                .sorted(Comparator.comparingInt(PromotionStep::order))
                .toList();
    }
}
