package org.example.insuranceManagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity(name="Beneficiary")
@Table(name="beneficiary",
uniqueConstraints={}
)
public class Beneficiary {
    @Id
    @SequenceGenerator(
        name = "beneficiary_sequence", 
        sequenceName = "beneficiary_sequence", 
        allocationSize = 1
    )
    @GeneratedValue(
        strategy=GenerationType.SEQUENCE, 
        generator = "beneficiary_sequence"
    )
    @Column(name = "id", updatable = false)
    private Long id;
    @Column(name="beneficiaries_name", nullable=false, columnDefinition="TEXT")
    private String beneficiariesName;
    @Column(name="beneficiaries_address", nullable=true, columnDefinition="TEXT")
    private String beneficiariesAddress;
    @Column(name="beneficiaries_phone", nullable=false, columnDefinition="TEXT")
    private String beneficiariesPhone;
    @Column(name="beneficiaries_email", nullable=false, columnDefinition="TEXT")
    private String beneficiariesEmail;
    @Column(name="beneficiaries_relationship", nullable=false, columnDefinition="TEXT")
    private String beneficiariesRelationship;
    @Column(name="beneficiaries_id_number", nullable=true, columnDefinition="TEXT")
    private String beneficiariesIdNumber;

    public Beneficiary(){}

    public Beneficiary(
        Long id,
        String beneficiariesName,
        String beneficiariesAddress,
        String beneficiariesPhone,
        String beneficiariesEmail,
        String beneficiariesRelationship,
        String beneficiariesIdNumber
    ){
        this.id = id;
        this.beneficiariesName = beneficiariesName;
        this.beneficiariesAddress = beneficiariesAddress;
        this.beneficiariesPhone = beneficiariesPhone;
        this.beneficiariesEmail = beneficiariesEmail;
        this.beneficiariesRelationship = beneficiariesRelationship;
        this.beneficiariesIdNumber = beneficiariesIdNumber;
    }

    public Beneficiary(
        String beneficiariesName,
        String beneficiariesAddress,
        String beneficiariesPhone,
        String beneficiariesEmail,
        String beneficiariesRelationship,
        String beneficiariesIdNumber
    ){
        this.beneficiariesName = beneficiariesName;
        this.beneficiariesAddress = beneficiariesAddress;
        this.beneficiariesPhone = beneficiariesPhone;
        this.beneficiariesEmail = beneficiariesEmail;
        this.beneficiariesRelationship = beneficiariesRelationship;
        this.beneficiariesIdNumber = beneficiariesIdNumber;
    }

    //getters and setters
    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getBeneficiariesName(){
        return beneficiariesName;
    }

    public void setBeneficiariesName(String beneficiariesName){
        this.beneficiariesName = beneficiariesName;
    }

    public String getBeneficiariesAddress(){
        return beneficiariesAddress;
    }

    public void setBeneficiariesAddress(String beneficiariesAddress){
        this.beneficiariesAddress = beneficiariesAddress;
    }

    public String getBeneficiariesPhone(){
        return beneficiariesPhone;
    }

    public void setBeneficiariesPhone(String beneficiariesPhone){
        this.beneficiariesPhone = beneficiariesPhone;
    }

    public String getBeneficiariesEmail(){
        return beneficiariesEmail;
    }

    public void setBeneficiariesEmail(String beneficiariesEmail){
        this.beneficiariesEmail = beneficiariesEmail;
    }

    public String getBeneficiariesRelationship(){
        return beneficiariesRelationship;
    }

    public void setBeneficiariesRelationship(String beneficiariesRelationship){
        this.beneficiariesRelationship = beneficiariesRelationship;
    }

    public String getBeneficiariesIdNumber(){
        return beneficiariesIdNumber;
    }

    public void setBeneficiariesIdNumber(String beneficiariesIdNumber){
        this.beneficiariesIdNumber = beneficiariesIdNumber;
    }

    @Override
    public String toString(){
        return "Beneficiary{" +
            "id=" + id +
            ", beneficiariesName='" + beneficiariesName + '\'' +
            ", beneficiariesAddress='" + beneficiariesAddress + '\'' +
            ", beneficiariesPhone='" + beneficiariesPhone + '\'' +
            ", beneficiariesEmail='" + beneficiariesEmail + '\'' +
            ", beneficiariesRelationship='" + beneficiariesRelationship + '\'' +
            ", beneficiariesIdNumber='" + beneficiariesIdNumber + '\'' +
        '}';
    }
}
