package com.team4.Transpeur.Model.DTO;

import com.sun.istack.NotNull;
import com.team4.Transpeur.Model.Entities.Role;
import com.team4.Transpeur.Model.Entities.User;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Set;

public class UserDTO {
    private String username;
    private Long id;
    private Set<Role> roles;

    public UserDTO(User user) {
        this.username = user.getUsername();
        this.id = user.getId();
        this.roles = user.getRoles();
    };


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

    public UserDTO() {};
}
