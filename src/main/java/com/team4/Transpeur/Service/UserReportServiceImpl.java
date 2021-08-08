package com.team4.Transpeur.Service;

import com.team4.Transpeur.Model.Entities.Report;
import com.team4.Transpeur.Repositories.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserReportServiceImpl implements UserReportService{
    final ReportRepository reportRepository;
    @Autowired
    public UserReportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public List<Report> findAll() {
        return reportRepository.findAll();
    }

    @Override
    public Optional<Report> findById(Long id) {
        return reportRepository.findById(id);
    }

    @Override
    public Report save(Report report) {
        return reportRepository.saveAndFlush(report);
    }

    @Override
    public void deleteById(Long id) {
        reportRepository.deleteById(id);
    }
}
