package com.team4.Transpeur.Model.DTO;

import com.team4.Transpeur.Model.Entities.Role;
import com.team4.Transpeur.Model.Entities.User;

import java.util.Set;

public class UserDTO {
    private String username;
    private Long id;
    private Set<Role> roles;
    private Boolean active;
    private Boolean blocked;

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
