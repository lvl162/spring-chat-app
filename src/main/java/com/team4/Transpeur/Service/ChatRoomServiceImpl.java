package com.team4.Transpeur.Service;

import com.team4.Transpeur.Entities.ChatRoom;
import com.team4.Transpeur.Repositories.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ChatRoomServiceImpl implements ChatRoomService{
    final ChatRoomRepository chatRoomRepository;
    @Autowired
    public ChatRoomServiceImpl(ChatRoomRepository chatRoomRepository) {
        this.chatRoomRepository = chatRoomRepository;
    }

    @Override
    public ChatRoom findBySenderIdAndRecipientId(Long uAid, Long uBid, boolean createIfNotExist) {
        if (uAid.equals(uBid)) return null;
        for (ChatRoom chatRoom : chatRoomRepository.findAll()) {
            if ((chatRoom.getaUserId().equals(uAid) && chatRoom.getbUserId().equals(uBid)) ||
                    (chatRoom.getbUserId().equals(uAid) && chatRoom.getaUserId().equals(uBid))) {
                return chatRoom;
            }
        }
        if (!createIfNotExist) return null;
        ChatRoom chatRoom = chatRoomRepository.saveAndFlush(new ChatRoom(uAid, uBid));
        return chatRoom;
    }

    @Override
    public Optional<ChatRoom> findById(Long id) {
        return chatRoomRepository.findById(id);
    }
}
