package com.team4.Transpeur.Model.DTO;

import com.team4.Transpeur.Model.Entities.ChatRoom;

import java.util.Set;
import java.util.stream.Collectors;

public class ChatRoomDTO {
    private Long id;
    private Long aUserId;
    private Long idB;
    private Set<MessageDTO> messages;
    public ChatRoomDTO() {};
    public ChatRoomDTO(ChatRoom chatRoom) {
        this.aUserId = chatRoom.getaUserId();
        this.idB = chatRoom.getbUserId();
        this.id = chatRoom.getId();
        this.messages = chatRoom.getMessages().stream().map(m -> new MessageDTO(m)).collect(Collectors.toSet());
    };

    public Long getIdA() {
        return aUserId;
    }

    public void setIdA(Long idA) {
        this.aUserId = idA;
    }

    public Long getIdB() {
        return idB;
    }

    public void setIdB(Long idB) {
        this.idB = idB;
    }

    public Set<MessageDTO> getMessages() {
        return messages;
    }

    public void setMessages(Set<MessageDTO> messages) {
        this.messages = messages;
    }
}