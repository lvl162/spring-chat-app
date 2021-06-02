package com.team4.Transpeur.Model.DTO;

import com.team4.Transpeur.Model.Entities.UserInformation;

public class UserInformationDTO {

    private Long id;
    public String idCardNumber;
    public String phoneNumber;
    public String address;
    public Integer level;
    public String firstName;
    public String lastName;

    public UserInformationDTO(){}
    public UserInformationDTO(UserInformation userInformation) {
        this.id = userInformation.getId();
        this.idCardNumber = userInformation.getIdCardNumber();
        this.phoneNumber = userInformation.getPhoneNumber();
        this.address = userInformation.getAddress();
        this.level = userInformation.getLevel();
        this.firstName = userInformation.getFirstName();
        this.lastName = userInformation.getLastName();
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
