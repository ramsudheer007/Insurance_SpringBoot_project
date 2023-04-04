package org.example.insuranceManagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity(name = "Client")
@Table(name = "client",uniqueConstraints = {
    @UniqueConstraint(
        name = "client_email_unique",
        columnNames = "client_email"
        ),
    @UniqueConstraint(
        name = "client_phone_unique", 
        columnNames = "client_phone"
        ),
})
public class Client {
    @Id
    @SequenceGenerator(
        name = "client_sequence", 
        sequenceName = "client_sequence", 
        allocationSize = 1)
    @GeneratedValue(
        strategy=GenerationType.SEQUENCE, 
        generator = "client_sequence")
    @Column(
        name = "id", updatable = false)
    private Long id;
    @Column(name = "name", nullable = false, columnDefinition = "TEXT")
    private String name;
    @Column(name = "address", nullable = true, columnDefinition = "TEXT")
    private String address;
    @Column(name = "client_phone", nullable = false, columnDefinition = "TEXT")
    private String phone;
    @Column(name = "client_email", nullable = false, columnDefinition = "TEXT")
    private String email;
    @Column(name = "next_of_kin", nullable = true, columnDefinition = "TEXT")
    private String nextOfKin;
    @Column(name = "next_of_kin_phone", nullable = true, columnDefinition = "TEXT")
    private String nextOfKinPhone;
    @Column(name = "next_of_kin_email", nullable = true, columnDefinition = "TEXT")
    private String nextOfKinEmail;
   
    public Client(){}

    public Client(
        Long id,
        String name,
        String address,
        String phone,
        String email,
        String nextOfKin,
        String nextOfKinPhone,
        String nextOfKinEmail
    ) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.nextOfKin = nextOfKin;
        this.nextOfKinPhone = nextOfKinPhone;
        this.nextOfKinEmail = nextOfKinEmail;
    }

    public Client(
        String name,
        String address,
        String phone,
        String email,
        String nextOfKin,
        String nextOfKinPhone,
        String nextOfKinEmail
    ) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.nextOfKin = nextOfKin;
        this.nextOfKinPhone = nextOfKinPhone;
        this.nextOfKinEmail = nextOfKinEmail;
    }

    //getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public String getNextOfKin() {
        return nextOfKin;
    }

    public void setNextOfKin(String nextOfKin) {
        this.nextOfKin = nextOfKin;
    }

    public String getNextOfKinPhone() {
        return nextOfKinPhone;
    }

    public void setNextOfKinPhone(String nextOfKinPhone) {
        this.nextOfKinPhone = nextOfKinPhone;
    }

    public String getNextOfKinEmail() {
        return nextOfKinEmail;
    }

    public void setNextOfKinEmail(String nextOfKinEmail) {
        this.nextOfKinEmail = nextOfKinEmail;
    }

    @Override
    public String toString() {
        return "Client [address=" + address  + ", email=" + email + ", id=" + id
                + ", name=" + name + ", nextOfKin=" + nextOfKin + ", nextOfKinEmail=" + nextOfKinEmail
                + ", nextOfKinPhone=" + nextOfKinPhone + ", phone=" + phone + "]";
    }
}
