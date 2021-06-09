package com.team4.Transpeur.Controller;

import com.team4.Transpeur.Model.DTO.Payload.Request.ChangePasswordRequest;
import com.team4.Transpeur.Model.DTO.Payload.Request.LoginRequest;
import com.team4.Transpeur.Model.DTO.Payload.Request.SignupRequest;
import com.team4.Transpeur.Model.DTO.Payload.Respone.JwtResponse;
import com.team4.Transpeur.Model.DTO.Payload.Respone.MessageResponse;
import com.team4.Transpeur.Model.DTO.UserInformationDTO;
import com.team4.Transpeur.Model.Entities.*;
import com.team4.Transpeur.Security.jwt.JwtUtils;
import com.team4.Transpeur.Security.services.UserDetailsImpl;
import com.team4.Transpeur.Service.EmailService;
import com.team4.Transpeur.Service.PasswordResetService;
import com.team4.Transpeur.Service.RoleService;
import com.team4.Transpeur.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    AuthenticationManager authenticationManager;

    EmailService emailService;

    UserService userService;

    RoleService roleService;

    PasswordEncoder encoder;

    JwtUtils jwtUtils;

    PasswordResetService passwordResetService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserService userService, RoleService roleService,
                          PasswordEncoder encoder, JwtUtils jwtUtils
            , EmailService emailService, PasswordResetService passwordResetService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.roleService = roleService;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
        this.emailService = emailService;
        this.passwordResetService = passwordResetService;
    }

    @Autowired

    @GetMapping("/signout")
    public ResponseEntity<?> logOut() {
        ResponseCookie resCookie = ResponseCookie.from("accessToken", null).maxAge(0).path("/")
                .build();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, resCookie.toString()).body("You have signed out");
    }

    @PostMapping("/changePassword")
    public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordRequest changePassword) {
        Optional<User> user = userService.findByUsername(changePassword.getUsername());
        if (user.isPresent()) {
            System.out.println(user.get().getPassword());
            System.out.println(encoder.encode(changePassword.getOldPassword()));
            if (encoder.matches(changePassword.getOldPassword(), user.get().getPassword())) {
                userService.changePassword(user.get(), encoder.encode(changePassword.getNewPassword()));
                return ResponseEntity.ok(new MessageResponse("Successfully changed the password"));
            }
            else {
                return ResponseEntity.badRequest().body(new MessageResponse("Wrong password"));
            }

        }

        return ResponseEntity.badRequest().body(new MessageResponse("Username not found"));
    }
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        ResponseCookie resCookie = ResponseCookie.from("accessToken", jwt)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(1 * 24 * 60 * 60)
                .build();
//        ResponseCookie uidCookie = ResponseCookie.from("uid", userDetails.getId().toString())
//                .httpOnly(true)
//                .secure(true)
//                .path("/")
//                .maxAge(1 * 24 * 60 * 60)
//                .build();
        UserInformation us = userService.findById(userDetails.getId()).get().getInAu();
        UserInformationDTO info = new UserInformationDTO();
        if (us!= null) {
            info = new UserInformationDTO(us);
        }
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, resCookie.toString()).body(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles, info));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {
        if (userService.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userService.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleService.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleService.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
//                    case "mod":
//                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(modRole);
//
//                        break;
                    default:
                        Role userRole = roleService.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userService.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
    @PostMapping("/forgotPassword")
    public ResponseEntity<?> processForgotPasswordForm(@RequestParam("email") String userEmail, HttpServletRequest request) {

        // Lookup user in database by e-mail
        Optional<User> optional = userService.findByEmail(userEmail);

        if (!optional.isPresent()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Email not found"));
        } else {

            // Generate random 36-character string token for reset password
            User user = optional.get();
            PasswordResetToken passwordResetToken = new PasswordResetToken();
            passwordResetToken.setToken(UUID.randomUUID().toString());
            passwordResetToken.setUser(user);
            // Save token to database
            passwordResetService.save(passwordResetToken);

            String appUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();

            // Email message
            SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
            passwordResetEmail.setFrom("support@demo.com");
            passwordResetEmail.setTo(user.getEmail());
            passwordResetEmail.setSubject("Password Reset Request");
            passwordResetEmail.setText("To reset your password, click the link below:\n" + appUrl
                    + "/api/auth/reset?token=" + passwordResetToken.getToken());

            emailService.sendEmail(passwordResetEmail);

            // Add success message to view
        }
        return ResponseEntity.ok(new MessageResponse("Reset password email has been sent"));
    }
    @GetMapping("/reset")
    public ResponseEntity<?> resetPassword(@PathParam("token") String token) {
//        if (passwordResetService.validatePasswordResetToken(token)){
//
//        };
        return null;
    }
}