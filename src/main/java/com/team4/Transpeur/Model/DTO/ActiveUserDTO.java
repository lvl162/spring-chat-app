package com.team4.Transpeur.Model.DTO;

import com.team4.Transpeur.Model.Entities.Role;
import com.team4.Transpeur.Model.Entities.User;

import java.util.Set;

public class ActiveUserDTO {
    private String username;
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private Boolean gender;
    public ActiveUserDTO(String username, Long id, String email, String firstName, String lastName, Boolean gender) {
        this.username = username;
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

    public ActiveUserDTO(User user) {
        this.username = user.getUsername();
        this.id = user.getId();
        this.email = user.getEmail();
        if (user.getInAu() != null) {
            this.firstName = user.getInAu().getFirstName();
            this.lastName = user.getInAu().lastName;
            this.gender = user.getInAu().getGender();
        }
        else {this.firstName = "UNKNOWN"; this.lastName = "";
            this.gender = true;}

    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
