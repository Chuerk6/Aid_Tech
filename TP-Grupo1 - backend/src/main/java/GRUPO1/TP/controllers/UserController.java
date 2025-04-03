package GRUPO1.TP.controllers;

import GRUPO1.TP.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import GRUPO1.TP.security.JwtUtilService;
import GRUPO1.TP.entities.User;
import GRUPO1.TP.services.UserService;
import GRUPO1.TP.dto.DTOUser;
import GRUPO1.TP.dto.DTOToken;
import GRUPO1.TP.security.SecurityUser;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;


import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    private JwtUtilService jwtUtilService;

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.listAll();
        return new ResponseEntity<>(users, HttpStatus.OK);

    }

    @PostMapping("/users/register")
    public ResponseEntity<User> createUser(@RequestBody DTOUser user) {
        User newUser = userService.register(user);
        return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }


    @PutMapping("/users")
    public ResponseEntity<User> updateUser(@RequestBody DTOUser user) {
        User newUser = userService.changePassword(user);
        return new ResponseEntity<User>(newUser, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        User user = userService.findById(id);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PostMapping("/users/login")
    public ResponseEntity<DTOToken> authenticate(@RequestBody DTOUser user) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));

        SecurityUser securityUser = (SecurityUser) this.userDetailsService.
                loadUserByUsername(user.getUserName());
        String jwt = jwtUtilService.generateToken(securityUser); //TOKEN-codigo largo
        Long id = securityUser.getUser().getId();

        String authoritiesString = securityUser.getUser().getAuthorities().stream()
                .map(n -> String.valueOf(n.getName().toString()))
                .collect(Collectors.joining(";", "", ""));
        //Devuelve por ejm: ROLE_ADMIN;ROLE_ADMIN

        //Imprime

        return new ResponseEntity<DTOToken>(new DTOToken(jwt, id, authoritiesString), HttpStatus.OK);

    }

    //no habia
    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}