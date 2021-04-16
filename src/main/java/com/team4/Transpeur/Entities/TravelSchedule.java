package com.team4.Transpeur.Entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="travelschedule", schema = "public")
public class TravelSchedule extends AuditModel{
    @Id
    @Column(name="id")
    private Long id;

    @Column(name="description")
    private String description;
    @Column(name="likes")
    private Integer likes;
    @Column(name="seen_times")
    private Integer seenTimes;
    @Column(name="from_place")
    private String fromPlace;
    @Column(name="to_place")
    private String toPlace;
    @Column(name="from_time")
    private Date fromTime;
    @Column(name="to_time")
    private Date toTime;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    @OneToMany()
    private Set<Contract> contracts;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getSeenTimes() {
        return seenTimes;
    }

    public void setSeenTimes(Integer seenTimes) {
        this.seenTimes = seenTimes;
    }

    public String getFromPlace() {
        return fromPlace;
    }

    public void setFromPlace(String fromPlace) {
        this.fromPlace = fromPlace;
    }

    public String getToPlace() {
        return toPlace;
    }

    public void setToPlace(String toPlace) {
        this.toPlace = toPlace;
    }

    public Date getFromTime() {
        return fromTime;
    }

    public void setFromTime(Date fromTime) {
        this.fromTime = fromTime;
    }

    public Date getToTime() {
        return toTime;
    }

    public void setToTime(Date toTime) {
        this.toTime = toTime;
    }
}
