package com.team4.Transpeur.Service;

import com.team4.Transpeur.Model.Entities.ChatRoom;
import com.team4.Transpeur.Model.Entities.User;
import com.team4.Transpeur.Repositories.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class ChatRoomServiceImpl implements ChatRoomService{
    final ChatRoomRepository chatRoomRepository;
    final UserService userService;
    @Autowired
    public ChatRoomServiceImpl(ChatRoomRepository chatRoomRepository, UserService userService) {
        this.chatRoomRepository = chatRoomRepository;
        this.userService = userService;
    }

//    static Predicate<ChatRoom> chatRoomPredicate() {
//        if (p.get)
//    }
    @Override
    public List<ChatRoom> findChatRoom(Long id) {
        try {
            return chatRoomRepository.findAll().stream()
                    .filter(chatRoom -> chatRoom.getaUserId().equals(id) || chatRoom.getbUserId().equals(id))
                    .sorted(new Comparator<ChatRoom>() {
                        @Override
                        public int compare(ChatRoom o1, ChatRoom o2) {
                            if (o1.getRecentActive() == null || o2.getRecentActive() == null) return 0;
                            return o1.getRecentActive().compareTo(o2.getRecentActive());
                        }
                    })
                    .distinct()
                    .collect(Collectors.toList());
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<ChatRoom>();
        }
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

    @Override
    public List<User> findByUserId(Long id) {
        try {
            return chatRoomRepository.findAll().stream()
                    .filter(chatRoom -> chatRoom.getaUserId().equals(id) || chatRoom.getbUserId().equals(id))
                    .sorted(new Comparator<ChatRoom>() {
                        @Override
                        public int compare(ChatRoom o1, ChatRoom o2) {
                            if (o1.getRecentActive() == null || o2.getRecentActive() == null) return 0;
                            return o2.getRecentActive().compareTo(o1.getRecentActive());
                        }

                    })
                    .map(chatRoom -> userService.findById(chatRoom.getaUserId().equals(id) ? chatRoom.getbUserId() : chatRoom.getaUserId()).get())
                    .distinct()
                    .collect(Collectors.toList());
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ChatRoom> findAll() {
        return chatRoomRepository.findAll();
    }

    @Override
    public void save(ChatRoom chat) {
        chatRoomRepository.save(chat);
    }
}
