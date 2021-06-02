package com.team4.Transpeur.Controller;

import com.team4.Transpeur.Model.Entities.User;
import com.team4.Transpeur.Repositories.UserRepository;
import com.team4.Transpeur.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.Optional;

@RestController
@RequestMapping("/api/users/")
public class UserController {
    private UserService userService;
    @Autowired private UserRepository userRepository;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public Page<User> getUsers(Pageable pageable) {

        return userService.findPageUser(pageable);
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable("id") Long id) {
        return userService.findById(id);

    }

    @GetMapping("paging")
    public ResponseEntity<?> getPageUser(@RequestParam("page") int pageNo, @RequestParam("size") int pageSize) {
        Pageable users = PageRequest.of( pageNo,pageSize);
        Page<User> page = userService.findPageUser(users);
        return ResponseEntity.ok().body(page);
    }
}
