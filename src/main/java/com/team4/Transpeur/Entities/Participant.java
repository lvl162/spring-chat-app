package com.team4.Transpeur.Entities;

import javax.persistence.*;

@Entity
@Table(name="participant", schema = "public")
public class Participant extends AuditModel {

    @Id
    @Column(name="id")
    private Long id;
    @ManyToOne
    @JoinColumn(name="conversation_id", nullable = false)
    private Conversation conversation;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
