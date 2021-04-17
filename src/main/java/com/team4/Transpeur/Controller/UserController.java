package com.team4.Transpeur.Controller;

import com.team4.Transpeur.Entities.User;
import com.team4.Transpeur.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/users")
    public Page<User> getUsers(Pageable pageable) {

        return userRepository.findAll(pageable);
    }
    @PostMapping("/users")
    public User createUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }
}
