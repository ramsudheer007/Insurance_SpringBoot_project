package org.example.insuranceManagement.repository;

import java.util.Optional;

import org.example.insuranceManagement.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

    Optional<Product> findByProductName( String productName);
    Optional<Product> findById(Long id);
    

}
