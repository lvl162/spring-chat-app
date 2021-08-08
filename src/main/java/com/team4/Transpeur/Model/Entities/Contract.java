package com.team4.Transpeur.Model.Entities;

import com.team4.Transpeur.Model.DTO.ContractDTO;

import javax.persistence.*;

@Entity
@Table(name="contract", schema = "public")
public class Contract extends AuditModel {
    @Id
    @Column(name="id")
    @GeneratedValue(generator = "user_generator")
    @SequenceGenerator(
            name = "user_generator",
            sequenceName = "user_sequence",
            initialValue = 1000
    )
    private Long id;

    @ManyToOne
    @JoinColumn(name="receiver_id", nullable = false)
    private User receiver;


    @Column(name="status")
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @ManyToOne
    @JoinColumn(name="travelschedule_id", nullable = false)
    private TravelSchedule travelSchedule;

    @Column(name="price")
    private Float price;
    @OneToOne(mappedBy = "contract", cascade=CascadeType.DETACH)
    @PrimaryKeyJoinColumn
    private Rating rating;

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public TravelSchedule getTravelSchedule() {
        return travelSchedule;
    }

    public void setTravelSchedule(TravelSchedule travelSchedule) {
        this.travelSchedule = travelSchedule;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name="description")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
