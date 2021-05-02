package com.team4.Transpeur.Controller;

import com.team4.Transpeur.Model.Entities.Message;
import com.team4.Transpeur.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/messages")
public class MessageController {
    final MessageService messageService;
    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Message> getAllMessages(@PathVariable String id) {
        if (id == null)
        return messageService.findAll();
        else return null;
    }

}
