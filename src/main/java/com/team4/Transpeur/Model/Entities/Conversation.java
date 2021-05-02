package com.team4.Transpeur.Model.Entities;

import javax.persistence.*;
import java.util.Set;
//@Entity
//@Table(name="conversation", schema = "public")
public class Conversation extends AuditModel {
    @Id
    @Column(name="id")
    private Long id;
    @Column(name="title")
    private String title;
    @Column(name="description")
    private String description;

    @OneToMany(mappedBy = "conversation")
    private Set<Message> messages;

    @ManyToOne
    @JoinColumn(name="creator_id", nullable = false)
    private User creator;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }
}
