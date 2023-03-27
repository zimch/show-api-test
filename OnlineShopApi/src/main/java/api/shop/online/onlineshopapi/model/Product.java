package api.shop.online.onlineshopapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.hibernate.annotations.Type;

import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "organization_id")
    @JsonBackReference
    private Organization organization;

    @Column(name = "price")
    private Double price;

    @Column(name = "quantity")
    private Long quantity;

//    @Type(ListArrayType.class)
//    @Column(
//            name = "keywords",
//            columnDefinition = "text[]"
//    )
//    private List<String> keywords;

    public Product() {
    }

    public Product(String description, Double price, Long quantity) {
        this.description = description;
        this.price = price;
        this.quantity = quantity;
//        this.keywords = keywords;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

//    public List<String> getKeywords() {
//        return keywords;
//    }

//    public void setKeywords(List<String> keywords) {
//        this.keywords = keywords;
//    }
}
