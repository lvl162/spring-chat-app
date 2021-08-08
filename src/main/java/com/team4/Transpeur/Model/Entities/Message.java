package com.team4.Transpeur.Model.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name="message", schema = "public")
public class Message extends AuditModel {
    @Id
    @Column(name="id")
    @GeneratedValue(generator = "user_generator")
    @SequenceGenerator(
            name = "user_generator",
            sequenceName = "user_sequence",
            initialValue = 1000
    )
    private Long id;

    @ManyToOne
    @JoinColumn(name="creator_id", nullable = false)
    @JsonBackReference
    private User creator;

//    @ManyToOne
//    @JoinColumn(name="conversation_id", nullable = false)
//    private Conversation conversation;
    @ManyToOne
    @JoinColumn(name="chatroom_id", nullable = false)
    @JsonBackReference
    private ChatRoom chatRoom;
    public enum MessageType {
        CHAT, JOIN, LEAVE
    }
    @Column(name="message_type")
    private MessageType messageType;
    @Column(name="content")
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public Conversation getConversation() {
//        return conversation;
//    }
//
//    public void setConversation(Conversation conversation) {
//        this.conversation = conversation;
//    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public ChatRoom getChatRoom() {
        return chatRoom;
    }

    public void setChatRoom(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
