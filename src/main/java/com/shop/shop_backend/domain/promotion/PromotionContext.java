package com.shop.shop_backend.domain.promotion;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class PromotionContext {
    // Discounts applied
    private BigDecimal subtotal;
    // Total discount accumulated so fat
    private BigDecimal discount = BigDecimal.ZERO;
    //Actual final amount that need to pay
    private BigDecimal payable;
    //Metadata map storing applied promotion codes and their respective discount values
    private Map<String,Object> meta = new HashMap<>();

    /**
     * Constructor initializing subtotal and payable amount.
     */
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
