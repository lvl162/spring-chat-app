package com.team4.Transpeur.Entities;

import javax.persistence.Column;

public class Report extends AuditModel {
    @Column(name="id")
    private Long id;
}
