package com.minhaz.productmanagement.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "charge_success_log")
public class ChargeSuccessLog extends BaseEntity {
    @Column(name = "sms_id")
    private long smsId;

    @Column(name = "transaction_id", length = 100)
    private String transactionId;

    @Column(name = "operator", length = 100)
    private String operator;

    @Column(name = "short_code", length = 100)
    private String shortCode;

    @Column(name = "msisdn", length = 100)
    private String msisdn;

    @Column(name = "keyword", length = 100)
    private String keyword;

    @Column(name = "game_name", length = 100)
    private String gameName;
}