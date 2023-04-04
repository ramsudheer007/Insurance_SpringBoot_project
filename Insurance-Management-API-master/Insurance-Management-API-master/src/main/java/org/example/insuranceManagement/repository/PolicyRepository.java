package org.example.insuranceManagement.repository;

import java.util.Optional;

import org.example.insuranceManagement.entity.Policy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PolicyRepository extends JpaRepository<Policy, Long> {
    Optional<Policy> findByPolicyNumber(String policyNumber);
    Optional<Policy> findById(Long id);
}
