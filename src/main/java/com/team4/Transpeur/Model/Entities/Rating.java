package com.team4.Transpeur.Model.Entities;

import javax.persistence.*;

@Entity
@Table(name="rating", schema = "public")
public class Rating extends AuditModel{
    @Id
    @Column(name="id")
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name="contract_id")
    private Contract contract;

    @Column(name="star")
    private Integer star;
    @Column(name="description")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
