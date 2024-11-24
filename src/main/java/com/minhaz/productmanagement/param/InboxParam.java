package com.minhaz.productmanagement.param;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InboxParam extends BaseParam {
    private UUID transactionId;
    private String operator;
    private Long shortCode;
    private Long msisdn;
    private String sms;
}
