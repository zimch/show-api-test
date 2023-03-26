package api.shop.online.onlineshopapi.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "balance")
    private Long balance;

//    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private String role = Role.USER.name();

//    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private String status = Status.ACTIVE.name();

    @OneToMany(mappedBy = "user")
//    @JoinColumn(name = "purchase_id")
    private Set<Purchase> purchases;

//    @OneToMany(mappedBy = "user")
//    @JoinColumn(name = "review_id")
//    private Set<Review> reviews;

    @OneToOne(mappedBy = "user")
    private Organization organization;

    public User() {
    }

    public User(String login, String email, String password, Long balance, String role,
                String status) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.balance = balance;
        this.role = role;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(Set<Purchase> purchases) {
        this.purchases = purchases;
    }

//    public Set<Review> getReviews() {
//        return reviews;
//    }

//    public void setReviews(Set<Review> reviews) {
//        this.reviews = reviews;
//    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}
