package com.sistema.bella_face.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "orderID")
    private Order order;

    @ManyToOne(optional = false)
    @JoinColumn(name = "productID")
    private Product product;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false, precision = 16, scale = 7)
    private BigDecimal unitPrice;

    @Column(nullable = false, precision = 16, scale = 7)
    private BigDecimal totalPrice;
}
