package com.team4.Transpeur.Model.DTO.Payload.Request;

import javax.validation.constraints.NotBlank;

public class GetChatIdRequest {
    @NotBlank
    private Long senderId;
    @NotBlank
    private Long recipientId;

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(Long recipientId) {
        this.recipientId = recipientId;
    }
}
