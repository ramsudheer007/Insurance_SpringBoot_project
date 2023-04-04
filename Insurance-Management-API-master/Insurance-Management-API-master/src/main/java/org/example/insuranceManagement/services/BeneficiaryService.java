package org.example.insuranceManagement.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.example.insuranceManagement.entity.Beneficiary;
import org.example.insuranceManagement.repository.BeneficiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class BeneficiaryService {
    private final BeneficiaryRepository beneficiaryRepository;

    @Autowired
    public BeneficiaryService(BeneficiaryRepository beneficiaryRepository){
        this.beneficiaryRepository=beneficiaryRepository;
    }

    public List<Beneficiary> getBeneficiaries(){
       return beneficiaryRepository.findAll();
    }

    public Beneficiary getBeneficiary(Long id){
        return beneficiaryRepository.findById(id).orElseThrow(()-> new IllegalStateException("Beneficiary with id " + id + " does not exist"));
    }
    
    public Beneficiary addBeneficiary(Beneficiary beneficiary){
        Optional<Beneficiary> beneficiaryOptional = beneficiaryRepository.findByBeneficiariesEmail(beneficiary.getBeneficiariesEmail());
        Optional<Beneficiary> beneficiaryOptional2 = beneficiaryRepository.findByBeneficiariesPhone(beneficiary.getBeneficiariesPhone());

        if(beneficiary.getBeneficiariesEmail() == null || beneficiary.getBeneficiariesEmail().isEmpty()){
            throw new IllegalStateException("Beneficiary email is required");
        }

        if(beneficiary.getBeneficiariesPhone() == null || beneficiary.getBeneficiariesPhone().isEmpty()){
            throw new IllegalStateException("Beneficiary phone is required");
        }

        if(beneficiaryOptional.isPresent()){
            throw new IllegalStateException("Beneficiary with this email already exists");
        }

        if(beneficiaryOptional2.isPresent()){
            throw new IllegalStateException("Beneficiary with this phone already exists");
        }

        if(beneficiary.getBeneficiariesName() == null || beneficiary.getBeneficiariesName().isEmpty()){
            throw new IllegalStateException("Beneficiary name is required");
        }

        if(!beneficiary.getBeneficiariesEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")){
            throw new IllegalStateException("Beneficiary email is not in correct format");
        }

        // if(!beneficiary.getBeneficiariesPhone().matches("^[0-9]{10}$")){
        //     throw new IllegalStateException("Beneficiary phone is not in correct format");
        // }

        if(beneficiary.getBeneficiariesRelationship() == null || beneficiary.getBeneficiariesRelationship().isEmpty()){
            throw new IllegalStateException("Beneficiary relationship is required");
        }

        Beneficiary addedBeneficiary= beneficiaryRepository.save(beneficiary);

        return addedBeneficiary;
    }

    public void deleteBeneficiary(Long id){
        boolean exists=beneficiaryRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("Beneficiary with id " + id + " does not exist");
        }
        beneficiaryRepository.deleteById(id);
    }

    @Transactional
    public Beneficiary updateBeneficiary(Long id, Beneficiary beneficiary){
        Beneficiary beneficiaryToUpdate= beneficiaryRepository.findById(id).orElseThrow(()-> new IllegalStateException("Beneficiary with id "+id+" does not exist"));

        if (beneficiary.getBeneficiariesName()!=null && 
        beneficiary.getBeneficiariesName().length()>0 && 
        !Objects.equals(beneficiaryToUpdate.getBeneficiariesName(),
        beneficiary.getBeneficiariesName())){
            beneficiaryToUpdate.setBeneficiariesName(
                beneficiary.getBeneficiariesName());
        }

        if (beneficiary.getBeneficiariesEmail()!=null &&
         beneficiary.getBeneficiariesEmail().length()>0 && 
         !Objects.equals(beneficiaryToUpdate.getBeneficiariesEmail(),
          beneficiary.getBeneficiariesEmail())){
            Optional<Beneficiary> beneficiaryOptional= beneficiaryRepository.findByBeneficiariesEmail(beneficiary.getBeneficiariesEmail());
            if(beneficiaryOptional.isPresent()){
                throw new IllegalStateException("Beneficiary with this email already exists");
            }

            beneficiaryToUpdate.setBeneficiariesEmail(beneficiary.getBeneficiariesEmail());
        }

        if(beneficiary.getBeneficiariesPhone()!=null && beneficiary.getBeneficiariesPhone().length()>0 && !Objects.equals(beneficiaryToUpdate.getBeneficiariesPhone(),beneficiary.getBeneficiariesPhone())){
            Optional<Beneficiary> beneficiaryOptional=beneficiaryRepository.findByBeneficiariesPhone(beneficiary.getBeneficiariesPhone());
            if(beneficiaryOptional.isPresent()){
                throw new IllegalStateException("Beneficiary with this phone already exists");
            }
        }

        if(beneficiary.getBeneficiariesRelationship()!=null && beneficiary.getBeneficiariesRelationship().length()>0 && !Objects.equals(beneficiaryToUpdate.getBeneficiariesRelationship(),beneficiary.getBeneficiariesRelationship())){
            beneficiaryToUpdate.setBeneficiariesRelationship(beneficiary.getBeneficiariesRelationship());
        }

        //validate address

        if(beneficiary.getBeneficiariesAddress()!=null && beneficiary.getBeneficiariesAddress().length()>0 && !Objects.equals(beneficiaryToUpdate.getBeneficiariesAddress(),beneficiary.getBeneficiariesAddress())){
            beneficiaryToUpdate.setBeneficiariesAddress(beneficiary.getBeneficiariesAddress());
        }

        //validate beneficiaries Id number
        if(beneficiary.getBeneficiariesIdNumber()!=null && beneficiary.getBeneficiariesIdNumber().length()>0 && !Objects.equals(beneficiaryToUpdate.getBeneficiariesIdNumber(),beneficiary.getBeneficiariesIdNumber())){
            beneficiaryToUpdate.setBeneficiariesIdNumber(beneficiary.getBeneficiariesIdNumber());
        }

        return beneficiaryToUpdate;


    }
}
