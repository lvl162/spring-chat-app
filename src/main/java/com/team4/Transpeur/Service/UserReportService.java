package com.team4.Transpeur.Service;


import com.team4.Transpeur.Model.Entities.Report;

import java.util.List;
import java.util.Optional;

public interface UserReportService {
    List<Report> findAll();
    Optional<Report> findById(Long id);
    void deleteById(Long id);
    Report save(Report report);
}
