package com.sistema.bella_face.repositories;

import com.sistema.bella_face.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
