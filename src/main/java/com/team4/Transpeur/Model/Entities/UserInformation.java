package com.team4.Transpeur.Model.Entities;

import com.team4.Transpeur.Model.DTO.UserInformationDTO;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "informationauthentication", schema = "public")
public class UserInformation extends AuditModel{
    @Id
    @Column(name="user_id")
    private Long id;
    @Column(name="id_card_number")
    private String idCardNumber;
    @Column(name="phone_number")
    private String phoneNumber;
    @Column(name="address")
    private String address;
    @Column(name="level")
    private Integer level;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    @Column(name="dob")
    private Date dob;
    @Column(name="age")
    private Integer age;
    public boolean getGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    @Column(name="gender", columnDefinition = "boolean default true")
    private Boolean gender;

    public UserInformation(UserInformationDTO userInformationDTO) {
        this.idCardNumber = userInformationDTO.getIdCardNumber();
        this.phoneNumber = userInformationDTO.getPhoneNumber();
        this.firstName = userInformationDTO.getFirstName();
        this.lastName = userInformationDTO.getLastName();
        this.level = userInformationDTO.getLevel();
        this.gender = userInformationDTO.getGender();
        this.age = userInformationDTO.getAge();
        this.dob = userInformationDTO.getDob();
    }

    public UserInformation() {

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name="firstname")
    public String firstName;
    @Column(name="lastname")
    public String lastName;
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
