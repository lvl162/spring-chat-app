package com.team4.Transpeur.Model.DTO;

import com.team4.Transpeur.Model.Entities.Report;


public class ReportDTO {

    private Long id;

    private String content;

    private String reportType;

    private Long creatorId;

    private UserInformationDTO creatorInfo;

    private Boolean solved, seen;

    public Boolean getSolved() {
        return solved;
    }

    public void setSolved(Boolean solved) {
        this.solved = solved;
    }

    public Boolean getSeen() {
        return seen;
    }

    public void setSeen(Boolean seen) {
        this.seen = seen;
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

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public UserInformationDTO getCreatorInfo() {
        return creatorInfo;
    }

    public void setCreatorInfo(UserInformationDTO creatorInfo) {
        this.creatorInfo = creatorInfo;
    }
    public ReportDTO() {}
    public ReportDTO(String content, String reportType, Long creatorId) {
        this.content = content;
        this.reportType = reportType;
        this.creatorId = creatorId;
        this.solved = false;
        this.seen = false;
    }

    public ReportDTO(Long id, String content, String reportType, Long creatorId, UserInformationDTO creatorInfo, Boolean solved, Boolean seen) {
        this.id = id;
        this.content = content;
        this.reportType = reportType;
        this.creatorId = creatorId;
        this.creatorInfo = creatorInfo;
        this.solved = solved;
        this.seen = seen;
    }

    public ReportDTO(Report report) {
        this.solved = report.getSolved();
        this.seen = report.getSeen();
        this.id = report.getId();
        this.content = report.getContent();
        this.reportType = report.getReportType();
        this.creatorId = report.getUser().getId();
        this.creatorInfo = report.getUser().getInAu() != null ? new UserInformationDTO(report.getUser().getInAu()): new UserInformationDTO();

    }
}
