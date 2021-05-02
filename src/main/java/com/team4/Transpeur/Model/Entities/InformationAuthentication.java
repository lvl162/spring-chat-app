package com.team4.Transpeur.Model.Entities;

import javax.persistence.*;

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

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
