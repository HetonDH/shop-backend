package com.shop.shop_backend.domain.promotion;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class PromotionContext {
    private BigDecimal subtotal;
    private BigDecimal discount = BigDecimal.ZERO;
    private BigDecimal payable;
    private Map<String,Object> meta = new HashMap<>();

    public PromotionContext(BigDecimal subtotal) {
        this.subtotal = subtotal;
        this.payable = subtotal;
    }

    public BigDecimal getSubtotal(){ return subtotal; }
    public BigDecimal getDiscount(){ return discount; }
    public BigDecimal getPayable(){ return payable; }
    public Map<String,Object> getMeta(){ return meta; }

    public void addDiscount(BigDecimal d, String code){
        if (d == null || d.signum() <= 0) return;
        discount = discount.add(d);
        payable = subtotal.subtract(discount);
        if (payable.signum() < 0) payable = BigDecimal.ZERO;
        meta.put(code, d);
    }
}
