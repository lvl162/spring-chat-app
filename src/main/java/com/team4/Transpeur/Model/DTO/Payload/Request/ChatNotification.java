package com.team4.Transpeur.Model.DTO.Payload.Request;

public class ChatNotification {
    private Long id;
    private Long senderId;
    private Long senderName;

    public ChatNotification(Long toString, Long senderId, Long senderName) {
        this.id = id;
        this.senderId = senderId;
        this.senderName = senderName;
    }
}