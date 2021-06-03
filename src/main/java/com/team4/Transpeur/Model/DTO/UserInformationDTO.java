package com.team4.Transpeur.Model.DTO;

import com.team4.Transpeur.Model.Entities.UserInformation;

public class UserInformationDTO {

    private Long id;
    private String idCardNumber;
    private String phoneNumber;
    private String address;
    private Integer level;
    private String firstName;
    private String lastName;
    private Boolean gender;
    private Integer age;

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public boolean getGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
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
