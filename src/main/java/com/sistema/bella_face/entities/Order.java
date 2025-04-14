package com.sistema.bella_face.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    @Id
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customerID")
    private Customer customer;

    @Column(nullable = false, precision = 16, scale = 7)
    private BigDecimal totalOrder;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false, length = 255)
    private String comments;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderProduct> items;
}
