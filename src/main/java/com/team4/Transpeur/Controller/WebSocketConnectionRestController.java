package com.team4.Transpeur.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;

import com.team4.Transpeur.Model.DTO.ActiveUserDTO;
import com.team4.Transpeur.Model.DTO.Payload.Respone.MessageResponse;
import com.team4.Transpeur.Model.DTO.UserDTO;
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
    
    private ActiveUserManager activeSessionManager;

    private UserService userService;

    @Autowired
    public WebSocketConnectionRestController(ActiveUserManager activeSessionManager, UserService userService) {
        this.activeSessionManager = activeSessionManager;
        this.userService = userService;
    }

    @GetMapping("/connect/{username}")
    public ResponseEntity<?> userConnect(HttpServletRequest request,
                                      @PathVariable("username") String userName) {
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
    public ResponseEntity<?> userDisconnect(HttpServletRequest request,
                              @PathVariable("username") String userName) {
        try {
            activeSessionManager.remove(userName);
            return ResponseEntity.ok().body(new MessageResponse("Success"));

        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(new MessageResponse("Error: " + e.getMessage()));
        }
    }
    
    @GetMapping("/active-users-except/{userName}")
    public ResponseEntity<?> getActiveUsersExceptCurrentUser(@PathVariable String userName) {
        List<ActiveUserDTO> users = new ArrayList<ActiveUserDTO>();
        for (String user: activeSessionManager.getActiveUsersExceptCurrentUser(userName) ) {
            Optional<User> userOptional = userService.findByUsername(user);
            if (userOptional.isPresent()) {
                users.add(new ActiveUserDTO(userOptional.get()));
            };
        };
        return ResponseEntity.ok().body(users);
    }
    @GetMapping("/active-users")
    public ResponseEntity<?> getActiveUsers() {
        List<ActiveUserDTO> users = new ArrayList<ActiveUserDTO>();
        for (String user: activeSessionManager.getAll() ) {
            Optional<User> userOptional = userService.findByUsername(user);
            if (userOptional.isPresent()) {
                users.add(new ActiveUserDTO(userOptional.get()));
            };
        };
        return ResponseEntity.ok().body(users);
    }
}