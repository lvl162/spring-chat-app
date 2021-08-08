package com.team4.Transpeur.Model.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
@Entity
@Table(name="chatroom", schema = "public")
public class ChatRoom extends AuditModel{
    @Id
    @Column(name="id")
    @GeneratedValue(generator = "user_generator")
    @SequenceGenerator(
            name = "user_generator",
            sequenceName = "user_sequence",
            initialValue = 1000
    )
    private Long Id;
    @Column(name="userA_id", nullable = false)
    private Long aUserId;
    @Column(name="userB_id", nullable = false)
    private Long bUserId;

    public Date getRecentActive() {
        return recentActive;
    }

    public void setRecentActive(Date recentActive) {
        this.recentActive = recentActive;
    }

    @Column(name="recent_active")
    @CreationTimestamp
    private Date recentActive;
    @OneToMany(mappedBy = "chatRoom", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<Message> messages;

    public ChatRoom() {

    }
    public ChatRoom(Long uA, Long uB) {
        this.aUserId = uA;
        this.bUserId = uB;
    }
    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

}
