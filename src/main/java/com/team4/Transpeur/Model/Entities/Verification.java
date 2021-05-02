package com.team4.Transpeur.Model.Entities;

import javax.persistence.*;
@Entity
@Table(name="verification", schema = "public")
public class Verification extends AuditModel{
    @Id
    @Column(name="user_id")
    private Long id;
    @Column(name="verification_code")
    private String verificationCode;
    @OneToOne
    @MapsId
    @JoinColumn(name="user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
