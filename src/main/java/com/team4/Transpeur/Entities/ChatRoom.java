package com.team4.Transpeur.Entities;

import javax.persistence.*;
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
    @OneToMany(mappedBy = "chatRoom", fetch = FetchType.LAZY)
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
