package com.minhaz.productmanagement.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@EqualsAndHashCode
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChargeConfigDto {
    private Long itemId;
    private String name;
    private String description;
    private BigDecimal price;
    private LocalDateTime createdAt;
}