package com.team4.Transpeur.Model.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.codelibs.elasticsearch.vi.analysis.VietnameseTokenizerFactory;
import org.hibernate.search.annotations.*;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="travelschedule", schema = "public")
@Indexed(index="idx_trvsch")
@AnalyzerDef(name = "customanalyzer",
        tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
        filters = {
                @TokenFilterDef(factory = LowerCaseFilterFactory.class)
        })
public class TravelSchedule extends AuditModel{
    @Id
    @Column(name="id")
    @GeneratedValue(generator = "user_generator")
    @SequenceGenerator(
            name = "user_generator",
            sequenceName = "user_sequence",
            initialValue = 1000
    )
    private Long id;

    @Column(name="description")
    @Field
    private String description;
    @Column(name="likes")
    private Integer likes;
    @Column(name="seen_times")
    private Integer seenTimes;
    @Column(name="from_place")
    @Field
    private String fromPlace;
    @Column(name="to_place")
    @Field
    private String toPlace;
    @Column(name="from_time")
//    @Field
    private Date fromTime;
    @Column(name="to_time")
//    @Field
    private Date toTime;

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    @Column(name="transport")
    @Field
    private String transport;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
//    @Field
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(Set<Contract> contracts) {
        this.contracts = contracts;
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

    public TravelSchedule(String description, String fromPlace, String toPlace, Date fromTime, Date toTime, User user, String transport) {
        this.description = description;
        this.fromPlace = fromPlace;
        this.toPlace = toPlace;
        this.fromTime = fromTime;
        this.toTime = toTime;
        this.user = user;
        this.transport = transport;
    }

    public TravelSchedule(){}
}
