package api.shop.online.onlineshopapi.rest;

import api.shop.online.onlineshopapi.model.*;
import api.shop.online.onlineshopapi.repository.OrganizationRepository;
import api.shop.online.onlineshopapi.repository.ProductRepository;
import api.shop.online.onlineshopapi.repository.UserRepository;
import api.shop.online.onlineshopapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/admin")
public class AdminRestController {
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrganizationRepository organizationRepository;


    // breakpoints for working with users (extra functionality)

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getUser() {

        List<User> users = userService.getAllUsers();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
        if (id == null || !userService.existUserById(id)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User user = this.userService.getUserById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

//    @PostMapping(value = "/users/save", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<User> saveUser(@RequestBody User user) {
//        HttpHeaders httpHeaders = new HttpHeaders();
//
//        if (user == null) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//
//        this.userService.save(user);
//
//        return new ResponseEntity<>(user, httpHeaders, HttpStatus.OK);
//    }

    @PutMapping(value = "/users/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user) {

        User userFromDB = userRepository.findById(id).orElseThrow();

        if (user.getBalance() != null) {
            userFromDB.setBalance(user.getBalance());
        }
        if (user.getStatus() != null) {
            userFromDB.setStatus(user.getStatus());
        }
        if (user.getRole() != null) {
            userFromDB.setRole(user.getRole());
        }

        userRepository.save(userFromDB);

        return new ResponseEntity<>(userFromDB, HttpStatus.OK);
    }

//    @DeleteMapping(value = "/users/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id) {
//        if (!userService.existUserById(id)) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        this.userService.delete(id);
//
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }

    @PutMapping(value = "/users/balance/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateBalance(@PathVariable("id") Long id, @RequestBody User user) {
        User balanceUpdateUser = userRepository.findById(id).orElseThrow();

        balanceUpdateUser.setBalance(user.getBalance());

        userRepository.save(balanceUpdateUser);

        return new ResponseEntity<>(balanceUpdateUser, HttpStatus.OK);
    }

    @PutMapping(value = "/edit/product/{prod_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> editProductInfo(@PathVariable("prod_id") Long prod_id, @RequestBody Product product) {
        Product productFromDB = productRepository.findById(prod_id).orElseThrow();

        if (product.getQuantity() != null) {
            productFromDB.setQuantity(product.getQuantity());
        }
        if (product.getPrice() != null) {
            productFromDB.setPrice(product.getPrice());
        }
        if (product.getDescription() != null) {
            productFromDB.setDescription(product.getDescription());
        }

        productRepository.save(productFromDB);

        return new ResponseEntity<>(productFromDB, HttpStatus.OK);
    }

    @PutMapping(value = "/edit/organization/{org_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Organization> editOrganization(@PathVariable("org_id") Long org_id, @RequestBody Organization organization) {
        Organization organizationFromDB = organizationRepository.findById(org_id).orElseThrow();

        if (organization.getStatus() != null) {
            organizationFromDB.setStatus(organization.getStatus());
        }
        if (organization.getLogo() != null) {
            organizationFromDB.setLogo(organization.getLogo());
        }
        if (organization.getName() != null) {
            organizationFromDB.setName(organization.getName());
        }
        if (organization.getDescription() != null) {
            organizationFromDB.setDescription(organization.getDescription());
        }

        organizationRepository.save(organizationFromDB);

        return new ResponseEntity<>(organizationFromDB, HttpStatus.OK);
    }

    @GetMapping(value = "/user/{id}/purchases", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Purchase>> getUserPurchases(@PathVariable("id") Long id) {
        User currentUser = userRepository.findById(id).orElseThrow();

        return new ResponseEntity<>(currentUser.getPurchases(), HttpStatus.OK);
    }
}
