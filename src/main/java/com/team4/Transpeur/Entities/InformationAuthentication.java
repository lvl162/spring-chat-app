package com.team4.Transpeur.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "informationauthentication", schema = "public")
public class InformationAuthentication extends AuditModel{
    @Id
    @Column(name="user_id")
    private Long id;
    @Column(name="id_card_number")
    public String idCardNumber;
    @Column(name="phone_number")
    public String phoneNumber;
    @Column(name="address")
    public String address;
    @Column(name="level")
    public Integer level;

}
