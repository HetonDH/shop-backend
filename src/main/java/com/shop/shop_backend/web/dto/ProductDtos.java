package com.shop.shop_backend.web.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;


public class ProductDtos {

    public record CreateReq(
            @NotBlank String title,
            String description,
            @NotNull @DecimalMin("0.00") BigDecimal price,
            @Size(max = 64) String sku,
            @Min(0) Integer initStock,
            @NotBlank String type,          // "SIMPLE", "VARIANT", "SERVICE"
            String variantKey,
            String serviceDuration
    ) {}

    public record Resp(
            Long id,
            String title,
            String description,
            BigDecimal price,
            String sku,
            String status,
            String type
    ) {}
}
