package com.team4.Transpeur.Service;

import com.team4.Transpeur.Model.Entities.ChatRoom;
import com.team4.Transpeur.Model.Entities.User;

import java.util.List;
import java.util.Optional;

public interface ChatRoomService {
    ChatRoom findBySenderIdAndRecipientId(Long senderId, Long recipientId, boolean createIfNotExist);
    Optional<ChatRoom> findById(Long id);
    List<User> findByUserId(Long id);

    List<ChatRoom> findAll();

    void save(ChatRoom chat);
}
