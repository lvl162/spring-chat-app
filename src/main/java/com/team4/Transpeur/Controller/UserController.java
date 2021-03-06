package com.team4.Transpeur.Controller;

import com.team4.Transpeur.Model.DTO.UserDTO;
import com.team4.Transpeur.Model.Entities.Rating;
import com.team4.Transpeur.Model.Entities.User;
import com.team4.Transpeur.Repositories.UserRepository;
import com.team4.Transpeur.Service.RatingService;
import com.team4.Transpeur.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users/")
public class UserController {
    private UserService userService;
    private RatingService ratingService;
    @Autowired private UserRepository userRepository;
    @Autowired
    public UserController(UserService userService, RatingService ratingService) {
        this.userService = userService;
        this.ratingService = ratingService;
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getUsers() {

        return ResponseEntity.ok().body(userService.findAll()
                .stream()
//                .filter(m -> m.getRoles().stream().allMatch(role -> role.getName().toString().equals("ROLE_USER")))
                .map(m-> {
                    UserDTO temp = new UserDTO(m);
                    temp.setAvgRating(ratingService.getAvgRatingByUid(m.getId()));
                    return temp;
                })
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public UserDTO getUserById(@PathVariable("id") Long id) {
        return new UserDTO(userService.findById(id).get());

    }
    @GetMapping("/search")
    public ResponseEntity<?> searchUser(@RequestParam("q") String q) {
        return ResponseEntity.ok().body(userService.searchByUsername(q).stream().
                map(m-> {
                    UserDTO temp = new UserDTO(m);
                    temp.setAvgRating(ratingService.getAvgRatingByUid(m.getId()));
                    return temp;
                }).collect(Collectors.toList()));

    }
    @GetMapping("/paging")
    public ResponseEntity<?> getPageUser(@RequestParam("page") int pageNo, @RequestParam("size") int pageSize) {
        Pageable users = PageRequest.of( pageNo,pageSize);
        Page<UserDTO> page = userService.findPageUser(users).map(m-> {
            UserDTO temp = new UserDTO(m);
            temp.setAvgRating(ratingService.getAvgRatingByUid(m.getId()));
            return temp;
        });
        return ResponseEntity.ok().body(page);
    }

    @GetMapping("/block/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> blockOrUnblockUser(@PathVariable("id") Long id) {
        Optional<User> user = userService.findById(id);
        if (user.isPresent()) {
            User x = user.get();
            x.setIs_blocked(!x.isIs_blocked());
            return ResponseEntity.ok().body(new UserDTO(userService.save(x)));

        }
        return  ResponseEntity.badRequest().body("No id user found");
    }

}
