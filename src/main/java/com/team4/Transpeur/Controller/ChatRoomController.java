package com.team4.Transpeur.Controller;

import com.team4.Transpeur.Model.DTO.ChatRoomDTO;
import com.team4.Transpeur.Model.DTO.UserDTO;
import com.team4.Transpeur.Model.Entities.ChatRoom;
import com.team4.Transpeur.Model.Entities.Message;
import com.team4.Transpeur.Model.DTO.Payload.Request.ChatMessage;
import com.team4.Transpeur.Model.DTO.Payload.Request.GetChatIdRequest;
import com.team4.Transpeur.Model.Entities.User;
import com.team4.Transpeur.Service.ChatRoomService;
import com.team4.Transpeur.Service.MessageService;
import com.team4.Transpeur.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ChatRoomController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired private MessageService messageService;
    @Autowired private ChatRoomService chatRoomService;
    @Autowired private UserService userService;

//    @MessageMapping("/chat")
//    public void processMessage(@Payload ChatMessage chatMessage) {
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
//                chat.getId().toString(),"/queue/messages",
//               chatMessage);
////        messagingTemplate.convertAndSendToUser(
////                chatMessage.getRecipientId().toString(),"/queue/messages",
////                new ChatNotification(
////                        saved.getId(),
////                        chatMessage.getSenderId(),
////                        chatMessage.getRecipientId()));
//    }
    @PostMapping("/api/getChatId")
    public ResponseEntity<?> getChatId(@RequestBody GetChatIdRequest getChatIdRequest) {
        ChatRoomDTO chat = new ChatRoomDTO(chatRoomService
                .findBySenderIdAndRecipientId(getChatIdRequest.getSenderId(), getChatIdRequest.getRecipientId(), true));
        chat.setUserA(new UserDTO(userService.findById(chat.getaUserId()).get()));
        chat.setUserB(new UserDTO(userService.findById(chat.getbUserId()).get()));

        return ResponseEntity.ok().body(chat);
    }

    @GetMapping("/api/chatroom/me/{id}")
    public ResponseEntity<?> getAllChatedUser(@PathVariable("id") Long id)
    {

        List<UserDTO> chatedUser = chatRoomService.findByUserId(id)
                .stream()
                .map(m -> new UserDTO(m))
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(chatedUser);

    }
    @GetMapping("/api/chatroom/me2/{id}")
    public ResponseEntity<?> getAllChatedUser2(@PathVariable("id") Long id)
    {
        try
        {
            List<ChatRoomDTO> chatedUser = chatRoomService.findChatRoom(id)
                    .stream()
                    .map(m -> {
                        ChatRoomDTO chat = new ChatRoomDTO(m);
                        chat.setUserA(new UserDTO(userService.findById(chat.getaUserId()).get()));
                        chat.setUserB(new UserDTO(userService.findById(chat.getbUserId()).get()));
                        return chat;
                    })
                    .collect(Collectors.toList());

            return ResponseEntity.ok().body(chatedUser);
        }
        catch (Exception e) {
            return ResponseEntity.ok().body(e);
        }

    }
    @GetMapping("/api/chatroom/all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok().body(chatRoomService.findAll());
    }
}
