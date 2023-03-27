package api.shop.online.onlineshopapi.rest;

import api.shop.online.onlineshopapi.model.Organization;
import api.shop.online.onlineshopapi.model.Product;
import api.shop.online.onlineshopapi.model.User;
import api.shop.online.onlineshopapi.repository.OrganizationRepository;
import api.shop.online.onlineshopapi.repository.ProductRepository;
import api.shop.online.onlineshopapi.repository.UserRepository;
import api.shop.online.onlineshopapi.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserRestController {

    @Autowired
    UserService userService;

    @Autowired
    OrganizationRepository organizationRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

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

}
