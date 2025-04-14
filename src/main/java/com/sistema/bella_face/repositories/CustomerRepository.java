package com.sistema.bella_face.repositories;

import com.sistema.bella_face.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findByLogin(String login);
}
