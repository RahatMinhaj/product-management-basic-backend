package com.minhaz.productmanagement.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@EqualsAndHashCode
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleDto {
    private Long saleId;
    private ItemDto itemDto;
    private StoreDto storeDto;
    private Integer quantity;
    private BigDecimal salePrice;
    private LocalDateTime createdAt;
}