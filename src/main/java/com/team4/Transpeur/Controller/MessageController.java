package com.team4.Transpeur.Controller;

import com.team4.Transpeur.Model.BO.MessageBO;
import com.team4.Transpeur.Model.BO.Payload.Respone.MessageResponse;
import com.team4.Transpeur.Service.ChatRoomService;
import com.team4.Transpeur.Service.MessageService;
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
    @Autowired
    public MessageController(MessageService messageService, ChatRoomService chatRoomService) {
        this.messageService = messageService;
        this.chatRoomService = chatRoomService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMessageById(@PathVariable("id") Long id) {
        Optional<MessageBO> message = messageService.findById(id);
        if (message.isPresent()) {
            return ResponseEntity.ok().body(message);
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Id not found"));
    }

    @GetMapping("/all")
    public List<MessageBO> getAllMessages() {
        return messageService.findAll();
    }
    @GetMapping("/user/{username}")
    public ResponseEntity<?> getMessageByUsername(@PathVariable("username") String username) {
        List<MessageBO> message = messageService.findByUsername(username);
        if (message.size()>0) {
            return ResponseEntity.ok().body(message);
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Id not found"));
    }

    @GetMapping("/chatroom/{id}")
    public ResponseEntity<?> getMessageByUsername(@PathVariable("id") Long id) {
        List<MessageBO> messages = chatRoomService.findById(id).get().getMessages().stream()
                .map(message -> new MessageBO(message.getId(), message.getChatRoom().getId(), message.getCreator().getId(), message.getCreator().getUsername(),message.getContent(),
                        message.getMessageType() , message.getCreatedAt())).sorted(new Comparator<MessageBO>() {
                    @Override
                    public int compare(MessageBO o1, MessageBO o2) {
                        return o1.getCreatedAt().compareTo(o2.getCreatedAt());
                    }
                }).collect(Collectors.toList());

        if (messages != null) {
            return ResponseEntity.ok().body(messages);
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Id not found"));
    }


}
