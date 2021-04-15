package com.team4.Transpeur.Entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="user", schema = "public")
public class User extends AuditModel {
    @Id
    @GeneratedValue(generator = "user_generator")
    @SequenceGenerator(
            name = "user_generator",
            sequenceName = "user_sequence",
            initialValue = 1000
    )
    @Column(name="id")
    private Long id;

    @NotBlank
    @Column(name="username", unique = true)
    private String username;

    @NotBlank
    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @NotBlank
    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "is_active", columnDefinition = "boolean default false")
    private boolean is_active;

    @Column(name = "is_blocked", columnDefinition = "boolean default false")
    private boolean is_blocked;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public boolean isIs_blocked() {
        return is_blocked;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public void setIs_blocked(boolean is_blocked) {
        this.is_blocked = is_blocked;
    }

}
