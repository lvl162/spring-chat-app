package com.team4.Transpeur.Model.Entities;

import javax.persistence.*;

@Entity
@Table(name="contract", schema = "public")
public class Contract extends AuditModel {
    @Id
    @Column(name="id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="receiver_id", nullable = false)
    private User receiver;

    @ManyToOne
    @JoinColumn(name="travelschedule_id", nullable = false)
    private TravelSchedule travelSchedule;

    @Column(name="description")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
