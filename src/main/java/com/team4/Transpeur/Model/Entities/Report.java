package com.team4.Transpeur.Model.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
@Entity
@Table(name="report", schema = "public")
public class Report extends AuditModel {
    @Id
    @GeneratedValue(generator = "user_generator")
    @SequenceGenerator(
            name = "user_generator",
            sequenceName = "user_sequence",
            initialValue = 1000
    )
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

    @Column(name="seen", columnDefinition = "boolean default false")
    private Boolean seen;
    @Column(name="solved", columnDefinition = "boolean default false")
    private Boolean solved;

    public Boolean getSeen() {
        return seen;
    }

    public void setSeen(Boolean seen) {
        this.seen = seen;
    }

    public Boolean getSolved() {
        return solved;
    }

    public void setSolved(Boolean solved) {
        this.solved = solved;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
