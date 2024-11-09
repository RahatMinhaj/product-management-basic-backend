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
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_id", unique = true)
    private Long purchaseId;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id", referencedColumnName = "item_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Item item;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "store_id", referencedColumnName = "store_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Store store;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "purchase_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal purchasePrice;

    @CreatedDate
    @Column(name = "purchase_date", nullable = false)
    private LocalDateTime createdAt;
}