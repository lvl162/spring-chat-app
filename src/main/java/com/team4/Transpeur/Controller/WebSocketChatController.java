package com.team4.Transpeur.Controller;

import com.team4.Transpeur.Model.DTO.Payload.Request.ChatMessage;
import com.team4.Transpeur.Model.Entities.ChatRoom;
import com.team4.Transpeur.Model.Entities.Message;
import com.team4.Transpeur.Service.ChatRoomService;
import com.team4.Transpeur.Service.MessageService;
import com.team4.Transpeur.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
@RestController
public class WebSocketChatController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private MessageService messageService;
    @Autowired
    private ChatRoomService chatRoomService;
    @Autowired
    private UserService userService;

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

//    @MessageMapping("/login")
//    public void login(@Payload ChatMessage chatMessage) {
//        ChatRoom chat = chatRoomService
//                .findBySenderIdAndRecipientId(chatMessage.getSenderId(), chatMessage.getRecipientId(), true);
//        chat.setRecentActive(new Date());
//        chatRoomService.save(chat);
//        Message message = new Message();
//        message.setChatRoom(chat);
//        message.setCreator(userService.findById(chatMessage.getSenderId()).get());
//        message.setMessageType(Message.MessageType.CHAT);
//        message.setContent(chatMessage.getContent());
//        Message saved = messageService.save(message);
//        messagingTemplate.convertAndSendToUser(
//                chat.getId().toString(), "/queue/messages",
//                chatMessage);
//    }
//    @MessageMapping("/logout")
//    public void logout(){
//
//    }
}
