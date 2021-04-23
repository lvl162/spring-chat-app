package com.team4.Transpeur.Controller;

import com.team4.Transpeur.Entities.ChatRoom;
import com.team4.Transpeur.Entities.Message;
import com.team4.Transpeur.Payload.Request.ChatMessage;
import com.team4.Transpeur.Payload.Request.GetChatIdRequest;
import com.team4.Transpeur.Service.ChatRoomService;
import com.team4.Transpeur.Service.MessageService;
import com.team4.Transpeur.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ChatRoomController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired private MessageService messageService;
    @Autowired private ChatRoomService chatRoomService;
    @Autowired private UserService userService;

    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessage chatMessage) {
        ChatRoom chat = chatRoomService
                .findBySenderIdAndRecipientId(chatMessage.getSenderId(), chatMessage.getRecipientId(), true);
        Message message = new Message();
        message.setChatRoom(chat);
        message.setCreator(userService.findById(chatMessage.getSenderId()).get());
        message.setMessageType(Message.MessageType.CHAT);
        message.setContent(chatMessage.getContent());
        Message saved = messageService.save(message);
        messagingTemplate.convertAndSendToUser(
                chat.getId().toString(),"/queue/messages",
               chatMessage);
//        messagingTemplate.convertAndSendToUser(
//                chatMessage.getRecipientId().toString(),"/queue/messages",
//                new ChatNotification(
//                        saved.getId(),
//                        chatMessage.getSenderId(),
//                        chatMessage.getRecipientId()));
    }

    @PostMapping("/api/getChatId")
    public ResponseEntity<?> getChatId(@RequestBody GetChatIdRequest getChatIdRequest) {
        ChatRoom chat = chatRoomService
                .findBySenderIdAndRecipientId(getChatIdRequest.getSenderId(), getChatIdRequest.getRecipientId(), true);
        chat.setMessages(null);
        return ResponseEntity.ok(chat);
    }

}
