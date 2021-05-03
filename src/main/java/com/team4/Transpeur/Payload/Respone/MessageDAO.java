package com.team4.Transpeur.Payload.Respone;

import com.team4.Transpeur.Model.Entities.Message;

import java.util.Date;

public class MessageDAO {
    private Long id;
    private Long chatRoomId;
    private Long creatorId;
    private String content;
    private Message.MessageType type;
    private Date createdAt;
    private String creatorName;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChatRoomId() {
        return chatRoomId;
    }

    public void setChatRoomId(Long chatRoomId) {
        this.chatRoomId = chatRoomId;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Message.MessageType getType() {
        return type;
    }

    public void setType(Message.MessageType type) {
        this.type = type;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public MessageDAO(Long id, Long chatRoomId, Long creatorId, String creatorName, String content, Message.MessageType type, Date createdAt) {
        this.id = id;
        this.chatRoomId = chatRoomId;
        this.creatorId = creatorId;
        this.content = content;
        this.type = type;
        this.createdAt = createdAt;
        this.creatorName = creatorName;
    }
}
