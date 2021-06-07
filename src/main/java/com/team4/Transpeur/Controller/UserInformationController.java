package com.team4.Transpeur.Controller;

import com.team4.Transpeur.Model.DTO.UserInformationDTO;
import com.team4.Transpeur.Model.Entities.User;
import com.team4.Transpeur.Model.Entities.UserInformation;
import com.team4.Transpeur.Service.UserInformationService;
import com.team4.Transpeur.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/user-information")
public class UserInformationController {
    final UserInformationService userInformationService;
    final UserService userService;
    @Autowired
    public UserInformationController(UserInformationService userInformationService, UserService userService) {
        this.userInformationService = userInformationService;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        Optional<UserInformation> uf = userInformationService.findUserInformationById(id);
        if (uf.isPresent())
        return ResponseEntity.ok().body(new UserInformationDTO(userInformationService.findUserInformationById(id).get()));
        else {
            return ResponseEntity.ok().body(new UserInformationDTO());
        }
    }
    @PutMapping("/modify")
    public ResponseEntity<?> modifyById(@RequestBody UserInformationDTO userInformation) {
        Optional<UserInformation> userIn = userInformationService.findUserInformationById(userInformation.getId());
        if (userIn.isPresent()) {
            UserInformation userInformation1 = userIn.get();
            userInformation1.setAddress(userInformation.getAddress());
            userInformation1.setFirstName(userInformation.getFirstName());
            userInformation1.setLastName(userInformation.getLastName());
            userInformation1.setPhoneNumber(userInformation.getPhoneNumber());
            userInformation1.setIdCardNumber(userInformation.getIdCardNumber());
            userInformation1.setUser(userService.findById(userInformation.getId()).get());
            userInformation1.setGender(userInformation.getGender());
            userInformation1.setDob(userInformation.getDob());
            userInformation1.setAge(userInformation.getAge());

            return ResponseEntity.ok().body(new UserInformationDTO(userInformationService.save(userInformation1)));
        } else {
            UserInformation userInformation1 = new UserInformation(userInformation);
            Optional<User> user = userService.findById(userInformation.getId());
            if (user.isPresent())
                userInformation1.setUser(user.get());
            return ResponseEntity.ok().body(new UserInformationDTO(userInformationService.save(userInformation1)));
        }
    }
    @PutMapping("/modifyByUname")
    public ResponseEntity<?> modifyByUname(@RequestBody UserInformationDTO userInformation) {
        System.out.println(userInformation.getUname());
        Optional<User> user1 = userService.findByUsername(userInformation.getUname());
        if (user1.isPresent()) {
            Optional<UserInformation> uf = userInformationService.findUserInformationById(user1.get().getId());
            if (uf.isPresent()) {
                System.out.println("yes");
                UserInformation uf2 = uf.get();
                uf2.setAge(userInformation.getAge());
                uf2.setDob(userInformation.getDob());
                uf2.setGender(userInformation.getGender());
                uf2.setAddress(userInformation.getAddress());
                uf2.setIdCardNumber(userInformation.getIdCardNumber());
                uf2.setFirstName(userInformation.getFirstName());
                uf2.setLastName(userInformation.getLastName());
                uf2.setPhoneNumber(userInformation.getPhoneNumber());
                return ResponseEntity.ok().body(new UserInformationDTO(userInformationService.save(uf2)));
            }
            return ResponseEntity.badRequest().body("Not found");

        }
        return ResponseEntity.badRequest().body("Not found");
//        if (user1.isPresent()) {
//            System.out.println(user1.get().getUsername());
//            Optional<UserInformation> userIn = userInformationService.findUserInformationById(user1.get().getId());
//            if (userIn.isPresent()) {
//                UserInformation userInformation1 = userIn.get();
//                userInformation1.setAddress(userInformation.getAddress());
//                userInformation1.setFirstName(userInformation.getFirstName());
//                userInformation1.setLastName(userInformation.getLastName());
//                userInformation1.setPhoneNumber(userInformation.getPhoneNumber());
//                userInformation1.setIdCardNumber(userInformation.getIdCardNumber());
//                userInformation1.setUser(userService.findById(userInformation.getId()).get());
//                userInformation1.setGender(userInformation.getGender());
//                userInformation1.setDob(userInformation.getDob());
//                userInformation1.setAge(userInformation.getAge());
//
//                return ResponseEntity.ok().body(new UserInformationDTO(userInformationService.save(userInformation1)));
//            } else {
//                UserInformation userInformation1 = new UserInformation(userInformation);
//                userInformation1.setUser(user1.get());
////                Optional<User> user = userService.findById(user1.get().getId());
////                if (user.isPresent())
////                    userInformation1.setUser(user.get());
//                return ResponseEntity.ok().body(userInformationService.save(userInformation1));
//            }
//        }
//        else {
//            return ResponseEntity.badRequest().body("Not found username");
//        }
    }
}
