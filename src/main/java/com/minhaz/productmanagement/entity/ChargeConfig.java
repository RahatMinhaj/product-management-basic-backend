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
@Table(name = "charge_config")
public class ChargeConfig extends BaseEntity{

    @Column(name = "operator", length = 100)
    private String operator;

    @Column(name = "charge_code", columnDefinition = "TEXT")
    private String chargeCode;
}
