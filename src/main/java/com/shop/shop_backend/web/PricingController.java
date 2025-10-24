package com.shop.shop_backend.web;

import com.shop.shop_backend.domain.promotion.PromotionContext;
import com.shop.shop_backend.service.PricingService;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pricing")
public class PricingController {

    private final PricingService pricing;
    public PricingController(PricingService pricing){
        this.pricing = pricing;
    }

    @PostMapping("/quote")
    public Map<String,Object> quote(
            @RequestParam @NotNull @DecimalMin("0.00") BigDecimal subtotal,
            @RequestBody(required = false) List<Map<String,Object>> rules
    ){
        PromotionContext ctx = pricing.quote(
                subtotal,
                rules == null ? List.of() : rules
        );

        return Map.of(
                "subtotal", ctx.getSubtotal(),
                "discount", ctx.getDiscount(),
                "payable", ctx.getPayable(),
                "meta", ctx.getMeta()
        );
    }
}
