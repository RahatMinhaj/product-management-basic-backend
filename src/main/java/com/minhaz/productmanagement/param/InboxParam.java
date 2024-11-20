package com.minhaz.productmanagement.param;

import jakarta.persistence.Column;
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
public class InboxParam extends BaseParam {
    private Long transactionId;
    private String operator;
    private String shortCode;
    private String msisdn;
    private String keyword;
    private String gameName;
    private String sms;
    private String status;
}
