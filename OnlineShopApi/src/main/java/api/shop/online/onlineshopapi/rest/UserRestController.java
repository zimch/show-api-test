package api.shop.online.onlineshopapi.rest;

import api.shop.online.onlineshopapi.model.Organization;
import api.shop.online.onlineshopapi.model.User;
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
    UserRepository userRepository;

    @GetMapping(value = "/organizations", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Organization> getOrganization(HttpServletRequest httpServletRequest) {
        Principal userPrincipal = httpServletRequest.getUserPrincipal();
        User user = userRepository.findByUsername(userPrincipal.getName());
        return new ResponseEntity<>(user.getOrganization(), HttpStatus.OK);
    }

}
