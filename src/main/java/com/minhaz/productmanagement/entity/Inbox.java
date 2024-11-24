package com.minhaz.productmanagement.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "charge_success_log")
public class Inbox extends BaseEntity {

    @Column(name = "transaction_id")
    private UUID transactionId;

    @Column(name = "operator", length = 100)
    private String operator;

    @Column(name = "short_code", length = 200)
    private String shortCode;

    @Column(name = "msisdn", nullable = false)
    private String msisdn;

    @Column(name = "keyword", nullable = false)
    private String keyword;

    @Column(name = "game_name", nullable = false)
    private String gameName;

    @Column(name = "sms", nullable = false)
    private String sms;

    @Column(name = "status", nullable = false)
    private String status;
}