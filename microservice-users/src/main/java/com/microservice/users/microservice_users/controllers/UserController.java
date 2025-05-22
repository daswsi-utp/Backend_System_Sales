package com.microservice.users.microservice_users.controllers;

import com.microservice.users.microservice_users.entities.User;
import com.microservice.users.microservice_users.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController
{
    @Autowired
    private IUserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        Optional<User> user = userService.findById(id);
        return user.map(us -> ResponseEntity.ok(us)).orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email){
        Optional<User> userOptional = userService.findByEmail(email);
        return  userOptional.map(us->ResponseEntity.ok(us)).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user,@PathVariable Long id){
        Optional<User> userUpdateOptional = userService.update(user,id);
        return  userUpdateOptional.map( userUpdate -> ResponseEntity.status(HttpStatus.CREATED).body(userUpdate))
                .orElseGet(()->ResponseEntity.noContent().build());

    }
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User savedUser =userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
