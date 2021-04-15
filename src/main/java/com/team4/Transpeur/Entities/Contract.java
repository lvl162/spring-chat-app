package com.team4.Transpeur.Entities;

import javax.persistence.Column;

public class Contract extends AuditModel {
    @Column(name="id")
    private Long id;
    @Column(name="id_user_a")
    private Long idUserA;
    @Column(name="id_user_b")
    private Long idUserB;
    @Column(name="id_travel_schedule")
    private Long idTravelSchedule;
    @Column(name="description")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUserA() {
        return idUserA;
    }

    public void setIdUserA(Long idUserA) {
        this.idUserA = idUserA;
    }

    public Long getIdUserB() {
        return idUserB;
    }

    public void setIdUserB(Long idUserB) {
        this.idUserB = idUserB;
    }

    public Long getIdTravelSchedule() {
        return idTravelSchedule;
    }

    public void setIdTravelSchedule(Long idTravelSchedule) {
        this.idTravelSchedule = idTravelSchedule;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
