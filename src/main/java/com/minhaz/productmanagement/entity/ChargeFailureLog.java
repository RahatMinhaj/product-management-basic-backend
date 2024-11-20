package com.minhaz.productmanagement.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "purchases")
@EntityListeners(AuditingEntityListener.class)
public class ChargeFailureLog extends BaseEntity {

    @Column(name = "sms_id", length = 100)
    private Long smsId;

    @Column(name = "transaction_id")
    private String transactionId;

    @Column(name = "operator")
    private String operator;

    @Column(name = "short_code", length = 100)
    private Long shortCode;

    @Column(name = "msisdn", length = 100)
    private Long msisdn;

    @Column(name = "keyword")
    private String keyword;

    @Column(name = "game_name")
    private String gameName;

    @Column(name = "status_code")
    private String statusCode;
    
    @Column(name = "message")
    private String message;
}