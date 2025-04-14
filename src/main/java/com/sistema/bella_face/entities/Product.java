package com.sistema.bella_face.entities;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    private Integer id;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(nullable = false, length = 100)
    private String description;

    @Column(nullable = false, precision = 16, scale = 7)
    private BigDecimal price;

    @Column(nullable = false)
    private LocalDate created;
}
