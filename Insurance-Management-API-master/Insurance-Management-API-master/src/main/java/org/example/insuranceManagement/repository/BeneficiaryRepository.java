package org.example.insuranceManagement.repository;

import java.util.Optional;

import org.example.insuranceManagement.entity.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long>{
    Optional<Beneficiary> findById(Long id);
    Optional<Beneficiary> findByBeneficiariesEmail(String beneficiariesEmail);
    Optional<Beneficiary> findByBeneficiariesPhone(String beneficiariesPhone);
 
    
}
