package com.team4.Transpeur.Repositories;

import com.team4.Transpeur.Model.Entities.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ReportRepository extends JpaRepository<Report, Long> {
}
