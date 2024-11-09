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
public class SaleParam {
    private Long saleId;
    private Long itemId;
    private Long storeId;
    @NotNull
    private Integer quantity;
    private BigDecimal salePrice;
}
