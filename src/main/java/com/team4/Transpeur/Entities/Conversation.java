package com.team4.Transpeur.Entities;

import javax.persistence.Column;

public class Conversation extends AuditModel {
    @Column(name="id")
    private Long id;
    @Column(name="title")
    private String title;
    @Column(name="description")
    private String description;
}
