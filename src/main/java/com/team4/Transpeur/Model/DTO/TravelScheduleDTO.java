package com.team4.Transpeur.Model.DTO;

import com.team4.Transpeur.Model.Entities.TravelSchedule;

import java.util.Date;

public class TravelScheduleDTO {
    private Long id;
    private String description;
    private Long creatorId;
    private String creatorUname;
    private Date createAt;
    private Integer likes;
    private Integer seenTimes;
    private String fromPlace;
    private String transport;

    private UserInformationDTO infoUser;
    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
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

    private String toPlace;
    private Date fromTime;
    private Date toTime;
    public TravelScheduleDTO() {};

    public UserInformationDTO getInfoUser() {
        return infoUser;
    }

    public void setInfoUser(UserInformationDTO infoUser) {
        this.infoUser = infoUser;
    }

    public TravelScheduleDTO(TravelSchedule travelSchedule) {

        this.id = travelSchedule.getId();
        this.description = travelSchedule.getDescription();
        this.createAt = travelSchedule.getCreatedAt();
        this.creatorId = travelSchedule.getUser().getId();
        this.creatorUname = travelSchedule.getUser().getUsername();
        this.likes = travelSchedule.getLikes();
        this.seenTimes = travelSchedule.getSeenTimes();
        this.fromPlace = travelSchedule.getFromPlace();
        this.toPlace = travelSchedule.getToPlace();
        this.fromTime = travelSchedule.getFromTime();
        this.toTime = travelSchedule.getToTime();
        this.transport= travelSchedule.getTransport();
        if (travelSchedule.getUser().getInAu()!=null) this.infoUser = new UserInformationDTO(travelSchedule.getUser().getInAu());
        else this.infoUser = new UserInformationDTO();
    }

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

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatorUname() {
        return creatorUname;
    }

    public void setCreatorUname(String creatorUname) {
        this.creatorUname = creatorUname;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
}
