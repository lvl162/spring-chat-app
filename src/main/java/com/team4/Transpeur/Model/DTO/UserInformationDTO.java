package com.team4.Transpeur.Model.DTO;

import com.team4.Transpeur.Model.Entities.UserInformation;

import java.util.Date;

public class UserInformationDTO {
    private String uname;
    private Float avgRating;

    public Float getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(Float avgRating) {
        this.avgRating = avgRating;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    private Long id;
    private String idCardNumber;
    private String phoneNumber;
    private String address;
    private Integer level;
    private String firstName;
    private String lastName;
    private Boolean gender;
    private Integer age;
    private Date dob;

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public UserInformationDTO(String uname, String idCardNumber, String phoneNumber, String address, Integer level, String firstName, String lastName, Boolean gender, Integer age, Date dob) {
        this.uname = uname;
        this.idCardNumber = idCardNumber;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.level = level;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.dob = dob;
        this.id = null;
    }

    public UserInformationDTO(Long id, String idCardNumber, String phoneNumber, String address, Integer level, String firstName, String lastName, Boolean gender, Integer age, Date dob) {
        this.id = id;
        this.idCardNumber = idCardNumber;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.level = level;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.dob = dob;
        this.uname = null;
    }

    public UserInformationDTO(){}
    public UserInformationDTO(UserInformation userInformation) {
        this.id = userInformation.getId();
        this.idCardNumber = userInformation.getIdCardNumber();
        this.phoneNumber = userInformation.getPhoneNumber();
        this.address = userInformation.getAddress();
        this.level = userInformation.getLevel();
        this.firstName = userInformation.getFirstName();
        this.lastName = userInformation.getLastName();
        this.gender = userInformation.getGender();
        this.age = userInformation.getAge();
        this.dob = userInformation.getDob();
        this.uname = userInformation.getUser().getUsername();
    }
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
}
