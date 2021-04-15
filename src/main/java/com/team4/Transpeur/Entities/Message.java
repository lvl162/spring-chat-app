package com.team4.Transpeur.Entities;

import javax.persistence.Column;

public class Message extends AuditModel {
    @Column(name="id")
    private Long id;
}
