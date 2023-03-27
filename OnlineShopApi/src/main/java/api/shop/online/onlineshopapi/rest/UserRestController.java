package api.shop.online.onlineshopapi.rest;

import api.shop.online.onlineshopapi.model.Organization;
import api.shop.online.onlineshopapi.model.User;
import api.shop.online.onlineshopapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserRestController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/organizations", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Organization> getAllOrganizations() {
        return new ResponseEntity<>(new Organization(), HttpStatus.OK);
    }

}
