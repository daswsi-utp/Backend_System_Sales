package com.microservice.users.microservice_users.service;

import com.microservice.users.microservice_users.entities.Role;
import com.microservice.users.microservice_users.entities.User;
import com.microservice.users.microservice_users.repositories.RoleRepository;
import com.microservice.users.microservice_users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));


        user.setRoles(getRoles());
        return userRepository.save(user);
    }




    @Override
    @Transactional
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }


    @Override
    public Optional<User> update(User user, Long id) {
        Optional<User> userOptional = this.findById(id);
        return userOptional.map(usDb ->{

            usDb.setEmail(user.getEmail());
            usDb.setName(user.getName());
            usDb.setCity(user.getCity());
            usDb.setCountry(user.getCountry());
            usDb.setLastName(user.getLastName());
            if(user.isEnabled() != null)
                usDb.setEnabled(user.isEnabled());
            user.setRoles(getRoles());
            return Optional.of(userRepository.save(usDb));
        }).orElseGet(()->Optional.empty());
    }

    private List<Role> getRoles() {
        List<Role> roles = new ArrayList<>();
        Optional<Role> roleOptional = roleRepository.findByName("ROLE_USER");
        roleOptional.ifPresent(r -> roles.add(r));
        return roles;
    }


}
