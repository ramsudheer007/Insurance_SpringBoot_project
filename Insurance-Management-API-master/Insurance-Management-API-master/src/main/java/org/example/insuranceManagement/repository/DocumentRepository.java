package org.example.insuranceManagement.repository;

import java.util.Optional;

import org.example.insuranceManagement.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
    Optional<Document> findById(Long id);
}
