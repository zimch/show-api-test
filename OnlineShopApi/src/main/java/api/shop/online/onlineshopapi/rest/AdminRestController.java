package api.shop.online.onlineshopapi.rest;

import api.shop.online.onlineshopapi.model.Status;
import api.shop.online.onlineshopapi.model.User;
import api.shop.online.onlineshopapi.repository.UserRepository;
import api.shop.online.onlineshopapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminRestController {
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;


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

    @PostMapping(value = "/users/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        HttpHeaders httpHeaders = new HttpHeaders();

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.userService.save(user);

        return new ResponseEntity<>(user, httpHeaders, HttpStatus.OK);
    }

    @PutMapping(value = "/users/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        HttpHeaders httpHeaders = new HttpHeaders();

        if (user == null ||!userService.existUserById(id)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.userService.save(user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

//    @PutMapping(value = "/users/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<User> updateUserStatus(@PathVariable("id") Long id, @RequestBody Status status) {
//        HttpHeaders httpHeaders = new HttpHeaders();
//
//        if (status == null ||!userService.existUserById(id)) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//
//        User user = userService.getUserById(id);
//        user.setStatus(status.name());
//        this.userService.save(user);
//
//        return new ResponseEntity<>(user, HttpStatus.OK);
//    }

    @DeleteMapping(value = "/users/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id) {
        if (!userService.existUserById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.userService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/users/balance/{id}")
    public ResponseEntity<User> updateBalance(@PathVariable("id") Long id, @RequestBody User user) {
        User balanceUpdateUser = userRepository.findById(id).orElseThrow();

        balanceUpdateUser.setBalance(user.getBalance());

        userRepository.save(balanceUpdateUser);

        return new ResponseEntity<>(balanceUpdateUser, HttpStatus.OK);
    }
}
