package org.example.insuranceManagement.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.example.insuranceManagement.entity.Beneficiary;
import org.example.insuranceManagement.entity.Client;
import org.example.insuranceManagement.entity.Policy;
import org.example.insuranceManagement.entity.Product;
import org.example.insuranceManagement.repository.BeneficiaryRepository;
import org.example.insuranceManagement.repository.ClientRepository;
import org.example.insuranceManagement.repository.PolicyRepository;
import org.example.insuranceManagement.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class PolicyService {
    private final PolicyRepository policyRepository;
    private final ProductRepository productRepository;
    private final ClientRepository clientRepository;
    private final BeneficiaryRepository beneficiaryRepository;

    @Autowired
    public PolicyService(PolicyRepository policyRepository,
     ProductRepository productRepository,
        ClientRepository clientRepository,
        BeneficiaryRepository beneficiaryRepository

     ){
        this.policyRepository=policyRepository;
        this.productRepository=productRepository;
        this.clientRepository=clientRepository;
        this.beneficiaryRepository=beneficiaryRepository;
    }

    public List<Policy> getPolicies(){
       return policyRepository.findAll();
    }

    public Policy getPolicy(Long id){
        Optional<Policy> clientOptional= policyRepository.findById(id);
        
        if(clientOptional.isEmpty()){
            throw new IllegalStateException("Policy with id " + id + " does not exist");
        }
        return clientOptional.get();
    }

    public Policy addPolicy(Policy policy){
        Optional<Policy> policyOptional = policyRepository.findByPolicyNumber(policy.getPolicyNumber());
      

        if(policy.getPolicyNumber() == null || policy.getPolicyNumber().isEmpty()){
            throw new IllegalStateException("Policy number is required");
        }

        if(policyOptional.isPresent()){
            throw new IllegalStateException("Policy with this policy number already exists");
        }

         if(policy.getProduct() == null){
           
            throw new IllegalStateException("Product is required");
        }
        
        Product product = productRepository.findById(policy.getProduct().getId()).orElse(null);
        if(product == null){
            throw new IllegalStateException("Product with id " + policy.getProduct().getId() + " does not exist");
        }
        policy.setProduct(product);
        
        if(policy.getClient() == null){
            throw new IllegalStateException("Client is required");
        }

        Client client = clientRepository.findById(policy.getClient().getId()).orElse(null);

        if(client == null){
            throw new IllegalStateException("Client with id " + policy.getClient().getId() + " does not exist");
        }
        policy.setClient(client);

        System.out.println("Policy issue date: " + LocalDate.now().toString());
        
        policy.setIssueDate(LocalDate.now());

        if(policy.getCoverageStartDate() == null){
            throw new IllegalStateException("Coverage start date is required");
        }

        String coverageStartDateString = policy.getCoverageStartDate().toString();
        String issueDateString = policy.getIssueDate().toString();

        LocalDate coverageStartDate = LocalDate.parse(coverageStartDateString);
        LocalDate issueDate = LocalDate.parse(issueDateString);

        if(coverageStartDate.isBefore(issueDate)){
            throw new IllegalStateException("Coverage start date cannot be before issue date");
        }

        if(policy.getCoverageEndDate() == null){
            throw new IllegalStateException("Coverage end date is required");
        }

        String coverageEndDateString = policy.getCoverageEndDate().toString();

        LocalDate coverageEndDate = LocalDate.parse(coverageEndDateString);

        if(coverageEndDate.isBefore(coverageStartDate)){
            throw new IllegalStateException("Coverage end date cannot be before coverage start date");
        }

        //ensure that the beneficiaries are not null
        if(policy.getBeneficiaries() == null || policy.getBeneficiaries().isEmpty()){
            throw new IllegalStateException("Beneficiaries are required");
        }

        for(Beneficiary beneficiary: policy.getBeneficiaries()){
            Beneficiary beneficiaryFromDb = beneficiaryRepository.findById(beneficiary.getId()).orElse(null);
            if(beneficiaryFromDb == null){
                throw new IllegalStateException("Beneficiary with id " + beneficiary.getId() + " does not exist");
            }


        }

        policy.setBeneficiaries(policy.getBeneficiaries());

     

        Policy addedPolicy= policyRepository.save(policy);

        return addedPolicy;
    }

    public void deletePolicy(Long id){
        boolean exists = policyRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("Policy with id " + id + " does not exist");
        }
        policyRepository.deleteById(id);
    }

    @Transactional
    public Policy updatePolicy(Long id, Policy policy){
        Policy policyFromDb = policyRepository.findById(id).orElseThrow(()-> new IllegalStateException("Policy with id " + id + " does not exist"));



        if(policy.getPolicyNumber() != null && 
        policy.getPolicyNumber().length() > 0 && 
        !Objects.equals(policyFromDb.getPolicyNumber(),
        policy.getPolicyNumber())){
            Optional<Policy> policyOptional = policyRepository.findByPolicyNumber(policy.getPolicyNumber());
            if(policyOptional.isPresent()){
                throw new IllegalStateException("Policy with this policy number already exists");
            }
            policyFromDb.setPolicyNumber(policy.getPolicyNumber());
        }

        if(policy.getProduct() != null){
            Product product = productRepository.findById(policy.getProduct().getId()).orElse(null);
            if(product == null){
                throw new IllegalStateException("Product with id " + policy.getProduct().getId() + " does not exist");
            }
            policyFromDb.setProduct(product);
        }

        if(policy.getClient() != null){
            Client client = clientRepository.findById(policy.getClient().getId()).orElse(null);
            if(client == null){
                throw new IllegalStateException("Client with id " + policy.getClient().getId() + " does not exist");
            }
            policyFromDb.setClient(client);
        }

        if(policy.getCoverageStartDate() != null){
            String coverageStartDateString = policy.getCoverageStartDate().toString();
            String issueDateString = policyFromDb.getIssueDate().toString();

            LocalDate coverageStartDate = LocalDate.parse(coverageStartDateString);
            LocalDate issueDate = LocalDate.parse(issueDateString);

            if(coverageStartDate.isBefore(issueDate)){
                throw new IllegalStateException("Coverage start date cannot be before issue date");
            }
            policyFromDb.setCoverageStartDate(coverageStartDate);
        }

        if(policy.getCoverageEndDate() != null){
            String coverageEndDateString = policy.getCoverageEndDate().toString();
            String coverageStartDateString = policyFromDb.getCoverageStartDate().toString();

            LocalDate coverageEndDate = LocalDate.parse(coverageEndDateString);
            LocalDate coverageStartDate = LocalDate.parse(coverageStartDateString);

            if(coverageEndDate.isBefore(coverageStartDate)){
                throw new IllegalStateException("Coverage end date cannot be before coverage start date");
            }
            policyFromDb.setCoverageEndDate(coverageEndDate);
        }

        if(policy.getBeneficiaries() != null && !policy.getBeneficiaries().isEmpty()){
            for(Beneficiary beneficiary: policy.getBeneficiaries()){
                Beneficiary beneficiaryFromDb = beneficiaryRepository.findById(beneficiary.getId()).orElse(null);
                if(beneficiaryFromDb == null){
                    throw new IllegalStateException("Beneficiary with id " + beneficiary.getId() + " does not exist");
                }
            }
            policyFromDb.setBeneficiaries(policy.getBeneficiaries());
        }

        return policyFromDb;
    }

}
