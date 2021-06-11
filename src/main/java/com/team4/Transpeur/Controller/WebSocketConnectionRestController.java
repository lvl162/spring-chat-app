package com.team4.Transpeur.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;

import com.team4.Transpeur.Model.DTO.ActiveUserDTO;
import com.team4.Transpeur.Model.DTO.Payload.Respone.MessageResponse;
import com.team4.Transpeur.Model.Entities.User;
import com.team4.Transpeur.Security.socket.ActiveUserManager;
import com.team4.Transpeur.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/active")
public class WebSocketConnectionRestController {
    
    final ActiveUserManager activeSessionManager;

    final UserService userService;

    @Autowired
    public WebSocketConnectionRestController(ActiveUserManager activeSessionManager, UserService userService) {
        this.activeSessionManager = activeSessionManager;
        this.userService = userService;
    }

    @GetMapping("/connect/{username}")
    public ResponseEntity<?> userConnect(HttpServletRequest request,
                                      @PathVariable("username") String userName) {
        if (!userService.findByUsername(userName).isPresent()) return ResponseEntity.badRequest().body(new
                MessageResponse("Error: not fount this username" ));
        try {

            String remoteAddr = "";
            if (request != null) {
                remoteAddr = request.getHeader("Remote_Addr");
                if (StringUtils.isEmpty(remoteAddr)) {
                    remoteAddr = request.getHeader("X-FORWARDED-FOR");
                    if (remoteAddr == null || "".equals(remoteAddr)) {
                        remoteAddr = request.getRemoteAddr();
                    }
                }
            }
            activeSessionManager.add(userName, remoteAddr);

            return ResponseEntity.ok().body(remoteAddr);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(new MessageResponse("Error: " + e.getMessage()));
        }
    }
    
    @GetMapping("/disconnect/{username}")
    public ResponseEntity<?> userDisconnect(
                              @PathVariable("username") String userName) {

        if (!userService.findByUsername(userName).isPresent()) return ResponseEntity.badRequest().body(new
                MessageResponse("Error: not fount this username" ));
        try {
            activeSessionManager.remove(userName);
            return ResponseEntity.ok().body(new MessageResponse("Success"));

        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(new MessageResponse("Error: " + e.getMessage()));
        }
    }
    @PostMapping(value = "/disconnect")
    public ResponseEntity<?> receiveSimpleBeacon(@RequestParam("data") String data) {
//        System.out.println(data + " "+ new Date().toString());
        if (!userService.findByUsername(data).isPresent()) return ResponseEntity.badRequest().body(new
                MessageResponse("Error: not fount this username" ));
        try {
            activeSessionManager.remove(data);
            return ResponseEntity.ok().body(new MessageResponse("Success"));

        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(new MessageResponse("Error: " + e.getMessage()));
        }
    }
    @GetMapping("/active-users-except/{userName}")
    public ResponseEntity<?> getActiveUsersExceptCurrentUser(@PathVariable String userName) {
        List<ActiveUserDTO> users = new ArrayList<>();
        for (String user: activeSessionManager.getActiveUsersExceptCurrentUser(userName) ) {
            Optional<User> userOptional = userService.findByUsername(user);
            userOptional.ifPresent(value -> users.add(new ActiveUserDTO(value)));
        }
        return ResponseEntity.ok().body(users);
    }
    @GetMapping("/active-users")
    public ResponseEntity<?> getActiveUsers() {
        List<ActiveUserDTO> users = new ArrayList<>();
        for (String user: activeSessionManager.getAll() ) {
            Optional<User> userOptional = userService.findByUsername(user);
            userOptional.ifPresent(value -> users.add(new ActiveUserDTO(value)));
        }
        return ResponseEntity.ok().body(users);
    }
}