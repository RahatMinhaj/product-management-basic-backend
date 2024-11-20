package com.minhaz.productmanagement.param;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChargeFailureLogParam extends BaseParam{
    private Long purchaseId;
    private Long itemId;
    private Long storeId;
    @NotNull
    private Integer quantity;
    private BigDecimal purchasePrice;
}
