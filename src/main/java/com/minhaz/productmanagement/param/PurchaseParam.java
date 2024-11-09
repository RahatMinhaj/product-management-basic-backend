package com.minhaz.productmanagement.param;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseParam {
    private Long purchaseId;
    private Long itemId;
    private Long storeId;
    @NotNull
    private Integer quantity;
    private BigDecimal purchasePrice;
}
