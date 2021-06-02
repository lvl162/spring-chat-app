package com.team4.Transpeur.Model.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
@Entity
@Table(name="report", schema = "public")
public class Report extends AuditModel {
    @Id
    @Column(name="id")
    private Long id;
    @Column(name="content")
    public String content;
    @Column(name="report_type")
    public String reportType;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;
}
