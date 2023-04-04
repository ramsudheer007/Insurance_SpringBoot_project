package org.example.insuranceManagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;


@Entity(name = "Product")
@Table(name = "product",
uniqueConstraints = {
    @UniqueConstraint(
        name = "product_name_unique", 
        columnNames = "product_name"
        )
})
public class Product {
    @Id
    @SequenceGenerator(
        name = "product_sequence", 
        sequenceName = "product_sequence", 
        allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "product_sequence")
    @Column(name = "id", updatable = false)
    private Long id;
    @Column(name = "product_name", nullable = false, columnDefinition = "TEXT")
    private String productName;
    @Column(name = "description", nullable = true, columnDefinition = "TEXT")
    private String description;
    @Column(name = "product_type", nullable = true, columnDefinition = "TEXT")
    private String productType;
    @Column(name = "price", nullable = false, columnDefinition = "float")
    private double price;

    public Product(){}

     public Product(
        Long id,
        String productName,
        String description,
        String productType,
        double price
    ) {
        this.id = id;
        this.productName = productName;
        this.description = description;
        this.productType = productType;
        this.price = price;
    }

    public Product(
        String productName,
        String description,
        String productType,
        double price
    ) {
        this.productName = productName;
        this.description = description;
        this.productType = productType;
        this.price = price;
    }

    //getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }


    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product [description=" + description + ", id=" + id + ", price=" + price + ", product_name=" + productName
                + ", product_type=" + productType + "]";
    }
     
    
}
