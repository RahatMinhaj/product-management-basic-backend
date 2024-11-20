package com.minhaz.productmanagement.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@EqualsAndHashCode
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChargeFailureLogDto {
    private Long purchaseId;
    private ChargeConfigDto chargeConfigDto;
    private KeywordDto keywordDto;
    private Integer quantity;
    private BigDecimal purchasePrice;
    private LocalDateTime createdAt;
}