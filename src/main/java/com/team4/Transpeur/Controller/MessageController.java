package com.team4.Transpeur.Controller;

import com.team4.Transpeur.Model.Entities.Message;
import com.team4.Transpeur.Payload.Respone.MessageDAO;
import com.team4.Transpeur.Payload.Respone.MessageResponse;
import com.team4.Transpeur.Repositories.ChatRoomRepository;
import com.team4.Transpeur.Service.ChatRoomService;
import com.team4.Transpeur.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/messages")
public class MessageController {
    final MessageService messageService;
    final ChatRoomService chatRoomService;
    @Autowired
    public MessageController(MessageService messageService, ChatRoomService chatRoomService) {
        this.messageService = messageService;
        this.chatRoomService = chatRoomService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMessageById(@PathVariable("id") Long id) {
        Optional<MessageDAO> message = messageService.findById(id);
        if (message.isPresent()) {
            return ResponseEntity.ok().body(message);
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Id not found"));
    }

    @GetMapping("/all")
    public List<MessageDAO> getAllMessages() {
        return messageService.findAll();
    }
    @GetMapping("/user/{username}")
    public ResponseEntity<?> getMessageByUsername(@PathVariable("username") String username) {
        List<MessageDAO> message = messageService.findByUsername(username);
        if (message.size()>0) {
            return ResponseEntity.ok().body(message);
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Id not found"));
    }

    @GetMapping("/chatroom/{id}")
    public ResponseEntity<?> getMessageByUsername(@PathVariable("id") Long id) {
        List<MessageDAO> messages = chatRoomService.findById(id).get().getMessages().stream()
                .map(message -> new MessageDAO(message.getId(), message.getChatRoom().getId(), message.getCreator().getId(), message.getCreator().getUsername(),message.getContent(),
                        message.getMessageType() , message.getCreatedAt())).sorted(new Comparator<MessageDAO>() {
                    @Override
                    public int compare(MessageDAO o1, MessageDAO o2) {
                        return o1.getCreatedAt().compareTo(o2.getCreatedAt());
                    }
                }).collect(Collectors.toList());

        if (messages != null) {
            return ResponseEntity.ok().body(messages);
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Id not found"));
    }


}
