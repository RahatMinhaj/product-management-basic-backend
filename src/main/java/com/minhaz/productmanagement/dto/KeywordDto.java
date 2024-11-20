package com.minhaz.productmanagement.dto;

import lombok.*;

import java.time.LocalDateTime;

@EqualsAndHashCode
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KeywordDto {
    private Long storeId;
    private String name;
    private String location;
    private LocalDateTime createdAt;
}