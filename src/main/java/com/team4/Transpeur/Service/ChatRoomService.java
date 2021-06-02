package com.team4.Transpeur.Service;

import com.team4.Transpeur.Model.Entities.ChatRoom;
import com.team4.Transpeur.Model.Entities.User;

import java.util.List;
import java.util.Optional;

public interface ChatRoomService {
    public ChatRoom findBySenderIdAndRecipientId(Long senderId, Long recipientId, boolean createIfNotExist);
    public Optional<ChatRoom> findById(Long id);
    public List<User> finByUserId(Long id);
}
