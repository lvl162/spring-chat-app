package com.team4.Transpeur.Model.DTO;

import com.team4.Transpeur.Model.Entities.Rating;
import com.team4.Transpeur.Model.Entities.Role;
import com.team4.Transpeur.Model.Entities.User;
import com.team4.Transpeur.Service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Set;

public class UserDTO {
    private String username;
    private Long id;
    private Set<Role> roles;
    private Boolean active;
    private Boolean blocked;

    private String email;

    private String firstName;
    private String lastName;

    private Integer numberOfPost;

    private Float avgRating;

    public Float getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(Float avgRating) {
        this.avgRating = avgRating;
    }

    public Integer getNumberOfPost() {
        return numberOfPost;
    }

    public void setNumberOfPost(Integer numberOfPost) {
        this.numberOfPost = numberOfPost;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }

    public UserDTO(User user) {
        this.username = user.getUsername();
        this.id = user.getId();
        this.roles = user.getRoles();
        this.blocked = user.isIs_blocked();
        this.active = user.isIs_active();
        this.email = user.getEmail();
        if (user.getInAu() != null) {
            this.firstName = user.getInAu().getFirstName();
            this.lastName = user.getInAu().getLastName();
        }
        else {
            this.firstName = null;
            this.lastName = null;
        }
        this.numberOfPost = user.getTravelSchedules().size();
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public UserDTO() {}
}
