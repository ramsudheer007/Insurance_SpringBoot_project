package org.example.insuranceManagement.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity(name="Document")
@Table(name="document",
uniqueConstraints={}
)
@JsonIdentityInfo(
  generator = ObjectIdGenerators.PropertyGenerator.class, 
  property = "id")
public class Document {
    @Id
    @SequenceGenerator(
        name = "document_sequence", 
        sequenceName = "document_sequence", 
        allocationSize = 1
    )
    @GeneratedValue(
        strategy=GenerationType.SEQUENCE, 
        generator = "document_sequence"
    )
    private Long id;
    @Column(name="document_path", nullable=true, columnDefinition="TEXT")
    private String documentPath;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "policy_id")
    private Policy policy;

   
    public Document(){}

    public Document(
        Long id,
        String documentPath,
        Policy policy) {
        this.id = id;
        this.documentPath = documentPath;
        this.policy = policy;
    }

      public Document(
        String documentPath,
        Policy policy) {
        this.documentPath = documentPath;
        this.policy = policy;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumentPath() {
        return this.documentPath;
    }

    public void setDocumentPath(String documentPath) {
        this.documentPath = documentPath;
    }

    public Policy getPolicy() {
        return this.policy;
    }

    public void setPolicy(Policy policy) {
        this.policy = policy;
    }

    public Document id(Long id) {
        setId(id);
        return this;
    }

    public Document documentPath(String documentPath) {
        setDocumentPath(documentPath);
        return this;
    }

    public Document policy(Policy policy) {
        setPolicy(policy);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", documentPath='" + getDocumentPath() + "'" +
            ", policy='" + getPolicy() + "'" +
            "}";
    }

}
