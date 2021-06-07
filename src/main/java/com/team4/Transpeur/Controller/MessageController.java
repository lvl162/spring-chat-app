package com.team4.Transpeur.Controller;

import com.team4.Transpeur.Model.DTO.MessageDTO;
import com.team4.Transpeur.Model.DTO.Payload.Respone.MessageResponse;
import com.team4.Transpeur.Service.ChatRoomService;
import com.team4.Transpeur.Service.MessageService;
import com.team4.Transpeur.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/messages")
public class MessageController {
    final MessageService messageService;
    final ChatRoomService chatRoomService;
    final UserService userService;
    @Autowired
    public MessageController(MessageService messageService, ChatRoomService chatRoomService, UserService userService) {
        this.messageService = messageService;
        this.chatRoomService = chatRoomService;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMessageById(@PathVariable("id") Long id) {
        Optional<MessageDTO> message = messageService.findById(id)
                .map(m -> new MessageDTO(m));
        if (message.isPresent()) {
            return ResponseEntity.ok().body(message);
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Id not found"));
    }

    @GetMapping("/all")
    public List<MessageDTO> getAllMessages() {
        return messageService.findAll()
                .stream()
                .map(m -> new MessageDTO(m))
                .collect(Collectors.toList());
    }

    @GetMapping("/uname/{username}")
    public ResponseEntity<?> getMessageByUsername(@PathVariable("username") String username) {
        List<MessageDTO> message = messageService.findByUsername(username)
                .stream()
                .map(m -> new MessageDTO(m))
                .collect(Collectors.toList());;
        if (message.size()>0) {
            return ResponseEntity.ok().body(message);
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Username not found"));
    }

    @GetMapping("/chatroom/{id}")
    public ResponseEntity<?> getMessageByUsername(@PathVariable("id") Long id) {
        List<MessageDTO> messages = chatRoomService.findById(id).get().getMessages().stream()
                .map(message -> new MessageDTO(message)).sorted(new Comparator<MessageDTO>() {
                    @Override
                    public int compare(MessageDTO o1, MessageDTO o2) {
                        return o1.getCreatedAt().compareTo(o2.getCreatedAt());
                    }
                }).collect(Collectors.toList());

        if (messages != null) {
            return ResponseEntity.ok().body(messages);
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Id not found"));
    }


}
