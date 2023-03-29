package api.shop.online.onlineshopapi.rest;

import api.shop.online.onlineshopapi.model.Organization;
import api.shop.online.onlineshopapi.model.Product;
import api.shop.online.onlineshopapi.model.Purchase;
import api.shop.online.onlineshopapi.model.User;
import api.shop.online.onlineshopapi.repository.OrganizationRepository;
import api.shop.online.onlineshopapi.repository.ProductRepository;
import api.shop.online.onlineshopapi.repository.PurchaseRepository;
import api.shop.online.onlineshopapi.repository.UserRepository;
import api.shop.online.onlineshopapi.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserRestController {

    @Autowired
    UserService userService;

    @Autowired
    OrganizationRepository organizationRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PurchaseRepository purchaseRepository;

    @GetMapping(value = "/organizations", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Organization> getOrganization(Principal principal) {
        User user = userRepository.findByUsername(principal.getName());
        return new ResponseEntity<>(user.getOrganization(), HttpStatus.OK);
    }

    @PostMapping(value = "/organizations/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Organization> saveUser(@RequestBody Organization organization,
                                                 Principal principal) {
        HttpHeaders httpHeaders = new HttpHeaders();

        if (organization == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        organization.setUser(userRepository.findByUsername(principal.getName()));

        this.organizationRepository.save(organization);

        return new ResponseEntity<>(organization, httpHeaders, HttpStatus.OK);
    }

    @PostMapping(value = "/organizations/add/product", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> addProduct(@RequestBody Product product,
                                              Principal principal) {
        HttpHeaders httpHeaders = new HttpHeaders();

        if (product == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User user = userRepository.findByUsername(principal.getName());

        if (user.getOrganization() != null) {
            product.setOrganization(user.getOrganization());
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.productRepository.save(product);

        return new ResponseEntity<>(product, httpHeaders, HttpStatus.OK);
    }

        @PostMapping(value = "/buy/product/{org_id}/{prod_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> buyProduct(@PathVariable("org_id") Long org_id, @PathVariable("prod_id") Long prod_id,
                                              Principal principal) {
        User currentUser = userRepository.findByUsername(principal.getName());
        Organization organization = organizationRepository.findById(org_id).orElseThrow();
        Product product = productRepository.findById(prod_id).orElseThrow();

        if (currentUser.getBalance() < product.getPrice()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }  else if (product.getQuantity() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            currentUser.changeBalance(-product.getPrice());
            product.changeQuantity();
            Set<Purchase> purchases = currentUser.getPurchases();
            Purchase purchase = new Purchase(product.getPrice(), new Date());
            purchase.setUser(currentUser);
//            purchase.setProduct(product);
            purchases.add(purchase);
            currentUser.setPurchases(purchases);
//            product.addPurchase(purchase);

            userRepository.save(currentUser);
            productRepository.save(product);
            purchaseRepository.save(purchase);
            organizationRepository.save(organization);
        }

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping(value = "/purchases", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Purchase>> getAllPurchases(Principal principal) {
        User currentUser = userRepository.findByLogin(principal.getName()).orElseThrow();

        return new ResponseEntity<>(currentUser.getPurchases(), HttpStatus.OK);
    }

}
