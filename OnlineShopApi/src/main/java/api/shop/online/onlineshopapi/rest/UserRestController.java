package api.shop.online.onlineshopapi.rest;

import api.shop.online.onlineshopapi.model.Organization;
import api.shop.online.onlineshopapi.model.User;
import api.shop.online.onlineshopapi.repository.OrganizationRepository;
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

}
