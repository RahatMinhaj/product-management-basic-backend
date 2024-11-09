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
public class ItemParam {
    private Long itemId;
    @NotNull
    private String name;
    private String description;
    @NotNull
    private BigDecimal price;
}
