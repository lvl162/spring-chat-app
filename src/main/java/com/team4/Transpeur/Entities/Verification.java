package com.team4.Transpeur.Entities;

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
}
