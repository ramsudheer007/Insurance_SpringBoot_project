package org.example.insuranceManagement.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;


@JsonIdentityInfo(
  generator = ObjectIdGenerators.PropertyGenerator.class, 
  property = "id")
@Entity(name = "Policy")
@Table(name = "policy", uniqueConstraints = {
    @UniqueConstraint(
        name = "policy_number_unique", 
        columnNames = "policy_number"),
})
public class Policy {
    @Id
    @SequenceGenerator(name = "policy_sequence", sequenceName = "policy_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "policy_sequence")
    @Column(name = "id", updatable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    
    private Product product;
    @Column(name = "policy_number", nullable = false, columnDefinition = "TEXT")
    private String policyNumber;
    @ManyToMany
    @JoinTable(
        name = "policy_beneficiary",
        joinColumns = @JoinColumn(name = "policy_id"),
        inverseJoinColumns = @JoinColumn(name = "beneficiary_id")
    )
    private List<Beneficiary> beneficiaries;


    @JsonManagedReference
    @OneToMany(mappedBy = "policy")
    private List<Document> documents;
    @Column(name = "issue_date", nullable = false, columnDefinition = "DATE")
    private LocalDate issueDate;
    @Column(name = "coverage_start_date", nullable = false, columnDefinition = "DATE")
    private LocalDate coverageStartDate;
    @Column(name = "coverage_end_date", nullable = false, columnDefinition = "DATE")
    private LocalDate coverageEndDate;


    public Policy() {
    }

    public Policy(
        Long id,
        Client client,
        Product product,
        String policyNumber,
        List<Beneficiary> beneficiaries,
        LocalDate issueDate,
        LocalDate coverageStartDate,
        LocalDate coverageEndDate
    ) {
        this.id = id;
        this.client = client;
        this.product = product;
        this.policyNumber = policyNumber;
        this.beneficiaries = new ArrayList<>();
        this.issueDate = issueDate;
        this.coverageStartDate = coverageStartDate;
        this.coverageEndDate = coverageEndDate;
    }

     public Policy(
        Client client,
        Product product,
        String policyNumber,
        List<Beneficiary> beneficiaries,
        LocalDate issueDate,
        LocalDate coverageStartDate,
        LocalDate coverageEndDate
    ) {
        this.client = client;
        this.product = product;
        this.policyNumber = policyNumber;
        this.beneficiaries = new ArrayList<>();
        this.issueDate = issueDate;
        this.coverageStartDate = coverageStartDate;
        this.coverageEndDate = coverageEndDate;
    }
    // Getters and Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return this.client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getPolicyNumber() {
        return this.policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public List<Beneficiary> getBeneficiaries() {
        return this.beneficiaries;
    }

    public void setBeneficiaries(List<Beneficiary> beneficiaries) {
        this.beneficiaries = beneficiaries;
    }

    public LocalDate getIssueDate() {
        return this.issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getCoverageStartDate() {
        return this.coverageStartDate;
    }

    public void setCoverageStartDate(LocalDate coverageStartDate) {
        this.coverageStartDate = coverageStartDate;
    }

    public LocalDate getCoverageEndDate() {
        return this.coverageEndDate;
    }

    public void setCoverageEndDate(LocalDate coverageEndDate) {
        this.coverageEndDate = coverageEndDate;
    }

    public List<Document> getDocuments() {
        return this.documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", client='" + getClient() + "'" +
            ", product='" + getProduct() + "'" +
            ", policyNumber='" + getPolicyNumber() + "'" +
            ", beneficiaries='" + getBeneficiaries() + "'" +
            ", issueDate='" + getIssueDate() + "'" +
            ", coverageStartDate='" + getCoverageStartDate() + "'" +
            ", coverageEndDate='" + getCoverageEndDate() + "'" +
            "}";
    }


}
