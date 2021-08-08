package com.team4.Transpeur.Model.DTO;

import com.team4.Transpeur.Model.Entities.Message;

import java.util.Date;

public class MessageDTO {
    private Long id;
    private Long senderId;
    private String senderName;
    private String content;
    private Date createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    public MessageDTO(){};

    public MessageDTO(Message message) {
        this.content = message.getContent();
        this.createdAt = message.getCreatedAt();
        this.id = message.getId();
        this.senderId = message.getCreator().getId();
        this.senderName = message.getCreator().getUsername();
    }
}
