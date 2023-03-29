package com.example.eurekaclient.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.Type;

import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "organization_id")
    @JsonBackReference
    private Organization organization;

//    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
////    @JsonManagedReference
////    @JsonIgnore
//    private List<Purchase> purchases;

    @Column(name = "price")
    private Long price;

    @Column(name = "quantity")
    private Long quantity;

    public Product() {
    }

    public Product(String name, String description, Long price, Long quantity) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public void changeQuantity() {
        this.quantity--;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public List<Purchase> getPurchases() {
//        return purchases;
//    }

//    public void setPurchases(List<Purchase> purchases) {
//        this.purchases = purchases;
//    }
//
//    public void addPurchase(Purchase purchase) {
//        this.purchases.add(purchase);
//    }

}
