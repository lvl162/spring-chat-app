package com.team4.Transpeur.Model.DTO;

import com.team4.Transpeur.Model.Entities.ChatRoom;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ChatRoomDTO {
    private Long id;
    private Long aUserId;
    private Long bUserId;
    private UserDTO userA;
    private UserDTO userB;
    private Set<MessageDTO> messages;
    public ChatRoomDTO() {};

    public UserDTO getUserA() {
        return userA;
    }

    public void setUserA(UserDTO userA) {
        this.userA = userA;
    }

    public UserDTO getUserB() {
        return userB;
    }

    public void setUserB(UserDTO userB) {
        this.userB = userB;
    }

    public ChatRoomDTO(ChatRoom chatRoom) {
        this.aUserId = chatRoom.getaUserId();
        this.bUserId = chatRoom.getbUserId();
        this.id = chatRoom.getId();
        if (chatRoom.getMessages() != null)
        this.messages = chatRoom.getMessages().stream().map(m -> new MessageDTO(m)).collect(Collectors.toSet());
        else this.messages = new HashSet<MessageDTO>();
    };

    public Long getIdA() {
        return aUserId;
    }

    public void setIdA(Long idA) {
        this.aUserId = idA;
    }

    public Long getIdB() {
        return bUserId;
    }

    public void setIdB(Long idB) {
        this.bUserId = idB;
    }

    public Set<MessageDTO> getMessages() {
        return messages;
    }

    public void setMessages(Set<MessageDTO> messages) {
        this.messages = messages;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getaUserId() {
        return aUserId;
    }

    public void setaUserId(Long aUserId) {
        this.aUserId = aUserId;
    }

    public Long getbUserId() {
        return bUserId;
    }

    public void setbUserId(Long bUserId) {
        this.bUserId = bUserId;
    }
}
