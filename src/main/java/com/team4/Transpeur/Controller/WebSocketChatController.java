package com.team4.Transpeur.Controller;

import com.team4.Transpeur.Model.DTO.ActiveUserDTO;
import com.team4.Transpeur.Model.DTO.Payload.Request.ChatMessage;
import com.team4.Transpeur.Model.Entities.ChatRoom;
import com.team4.Transpeur.Model.Entities.Message;
import com.team4.Transpeur.Model.Entities.User;
import com.team4.Transpeur.Security.socket.ActiveUserChangeListener;
import com.team4.Transpeur.Security.socket.ActiveUserManager;
import com.team4.Transpeur.Service.ChatRoomService;
import com.team4.Transpeur.Service.MessageService;
import com.team4.Transpeur.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class WebSocketChatController implements ActiveUserChangeListener  {
    private SimpMessagingTemplate messagingTemplate;
    private MessageService messageService;
    private ChatRoomService chatRoomService;
    private UserService userService;
    @Autowired
    private ActiveUserManager activeUserManager;

    @Override
    public void notifyActiveUserChange() {
//        System.out.println("notify nek");
        List<ActiveUserDTO> users = new ArrayList<ActiveUserDTO>();
        for (String user: activeUserManager.getAll() ) {
            Optional<User> userOptional = userService.findByUsername(user);
            if (userOptional.isPresent()) {
                users.add(new ActiveUserDTO(userOptional.get()));
            };
        }
        messagingTemplate.convertAndSend("/topic/active-users-list", users);
    }
    @PostConstruct
    private void init() {
        activeUserManager.registerListener(this);
    }

    @PreDestroy
    private void destroy() {
        activeUserManager.removeListener(this);
    }
    @Autowired
    public WebSocketChatController(SimpMessagingTemplate messagingTemplate, MessageService messageService, ChatRoomService chatRoomService, UserService userService) {
        this.messagingTemplate = messagingTemplate;
        this.messageService = messageService;
        this.chatRoomService = chatRoomService;
        this.userService = userService;
    }

    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessage chatMessage) {
        ChatRoom chat = chatRoomService
                .findBySenderIdAndRecipientId(chatMessage.getSenderId(), chatMessage.getRecipientId(), true);
        chat.setRecentActive(new Date());
        chatRoomService.save(chat);
        Message message = new Message();
        message.setChatRoom(chat);
        message.setCreator(userService.findById(chatMessage.getSenderId()).get());
        message.setMessageType(Message.MessageType.CHAT);
        message.setContent(chatMessage.getContent());
        Message saved = messageService.save(message);
        messagingTemplate.convertAndSendToUser(
                chat.getId().toString(), "/queue/messages",
                chatMessage);
    }
}
