package com.team4.Transpeur.Model.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
    public User() {
    }
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
    // user_role table
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @JsonManagedReference
    private Set<Role> roles = new HashSet<>();

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }





    // userverification table
    @OneToOne(mappedBy="user", cascade=CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Verification verification;
    public Verification getVerification() {
        return verification;
    }

    public void setVerification(Verification verification) {
        this.verification = verification;
    }


    // Info Auth
    @OneToOne(mappedBy = "user", cascade=CascadeType.DETACH)
    @PrimaryKeyJoinColumn
    private InformationAuthentication inAu;
    public InformationAuthentication getInAu() {
        return inAu;
    }

    public void setInAu(InformationAuthentication inAu) {
        this.inAu = inAu;
    }

    // messages
    @OneToMany(mappedBy="creator")
    @JsonManagedReference
    private Set<Message> messages;
    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    // participants
//    @OneToMany(mappedBy="user")
//    private Set<Participant> participants;
//    public Set<Participant> getParticipants() {
//        return participants;
//    }
//
//    public void setParticipants(Set<Participant> participants) {
//        this.participants = participants;
//    }


    //travel schedules
    @OneToMany(mappedBy="user")
    @JsonManagedReference
    private Set<TravelSchedule> travelSchedules;
    public Set<TravelSchedule> getTravelSchedules() {
        return travelSchedules;
    }

    public void setTravelSchedules(Set<TravelSchedule> travelSchedules) {
        this.travelSchedules = travelSchedules;
    }

    // reports

    @OneToMany(mappedBy="user")
    @JsonManagedReference
    private Set<Report> reports;
    public Set<Report> getReports() {
        return reports;
    }

    public void setReports(Set<Report> reports) {
        this.reports = reports;
    }


    //  contract

//    @OneToMany(mappedBy="user")
//    private Set<Contract> contracts;
//    public Set<Contract> getContracts() {
//        return contracts;
//    }
//
//    public void setContracts(Set<Contract> contracts) {
//        this.contracts = contracts;
//    }


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

    public void setIs_blocked(boolean is_blocked) {
        this.is_blocked = is_blocked;
    }

}
