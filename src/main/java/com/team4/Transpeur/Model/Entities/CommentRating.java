package com.team4.Transpeur.Model.Entities;

import javax.persistence.*;

public class CommentRating extends AuditModel{
    @Column(name="id")
    private Long id;
    @Column(name="rate_user_id")
    private long ratedUserId;
    @Column(name="comment")
    private String description;
    @Column(name="rate_point")
    private int ratePoint;
}
