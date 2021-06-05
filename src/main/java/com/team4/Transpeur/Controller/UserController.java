package com.team4.Transpeur.Controller;

import com.team4.Transpeur.Model.DTO.UserDTO;
import com.team4.Transpeur.Model.Entities.User;
import com.team4.Transpeur.Repositories.UserRepository;
import com.team4.Transpeur.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.stream.Collectors;

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
    public Page<UserDTO> getUsers(Pageable pageable) {

        Page<User> p =  userService.findPageUser(pageable);

        return p.map(m-> new UserDTO(m));
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable("id") Long id) {
        return new UserDTO(userService.findById(id).get());

    }
    @GetMapping("/search")
    public ResponseEntity<?> searchUser(@RequestParam("q") String q) {
        return ResponseEntity.ok().body(userService.searchByUsername(q).stream().
                map(UserDTO::new).collect(Collectors.toList()));

    }
    @GetMapping("/paging")
    public ResponseEntity<?> getPageUser(@RequestParam("page") int pageNo, @RequestParam("size") int pageSize) {
        Pageable users = PageRequest.of( pageNo,pageSize);
        Page<UserDTO> page = userService.findPageUser(users).map(m-> new UserDTO(m));
        return ResponseEntity.ok().body(page);
    }


}
