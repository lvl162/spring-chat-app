package com.team4.Transpeur.Model.DTO;

import com.team4.Transpeur.Model.Entities.TravelSchedule;

import java.util.Date;

public class TravelScheduleDTO {
    private Long id;
    private String description;
    private Long creatorId;
    private String creatorUname;
    private Date createAt;

    public TravelScheduleDTO() {};
    public TravelScheduleDTO(TravelSchedule travelSchedule) {
        this.id = travelSchedule.getId();
        this.description = travelSchedule.getDescription();
        this.createAt = travelSchedule.getCreatedAt();
        this.creatorId = travelSchedule.getUser().getId();
        this.creatorUname = travelSchedule.getUser().getUsername();
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
