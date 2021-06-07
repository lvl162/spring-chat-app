package com.team4.Transpeur.Controller;

import com.team4.Transpeur.Model.DTO.ReportDTO;
import com.team4.Transpeur.Model.Entities.Report;
import com.team4.Transpeur.Model.Entities.User;
import com.team4.Transpeur.Service.UserReportService;
import com.team4.Transpeur.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/report")
public class UserReportController {
    final UserReportService reportService;
    final UserService userService;
    @Autowired
    public UserReportController(UserReportService reportService, UserService userService) {
        this.reportService = reportService;
        this.userService = userService;
    }

    @PostMapping("/new")
    public ResponseEntity<?> newReport(@Valid @RequestBody ReportDTO report) {
        try
        {
            Report save = new Report();
            save.setContent(report.getContent());
            save.setReportType(report.getReportType());
            save.setUser(userService.findById(report.getCreatorId()).get());
            save.setSolved(false);
            save.setSeen(false);
            return ResponseEntity.ok().body(reportService.save(save));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e);

        }

    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok().body(reportService.findAll());
    }

    @GetMapping("/uid/{id}")
    public ResponseEntity<?> getByUid(@PathVariable("id") Long id) {
        Optional<User> user = userService.findById(id);
        return ResponseEntity.ok().body(user.get().getReports().stream().
                map(ReportDTO::new).collect(Collectors.toList()));
    }

    @GetMapping("/uname/{uname}")
    public ResponseEntity<?> getByUname(@PathVariable("uname") String uname) {
        Optional<User> user = userService.findByUsername(uname);
        return ResponseEntity.ok().body(user.get().getReports().stream().
                map(ReportDTO::new).collect(Collectors.toList()));
    }
    @PutMapping("/modify")
    public ResponseEntity<?> ModyReport(@Valid @RequestBody ReportDTO r) {
        Optional<Report> report = reportService.findById(r.getId());

        if (report.isPresent())
        {
            Report save = report.get();
            save.setId(r.getId());
            save.setReportType(r.getReportType());
            save.setContent(r.getContent());
            save.setUser(userService.findById(r.getCreatorId()).get());
            save.setSeen(r.getSeen());
            save.setSolved(r.getSolved());
            return ResponseEntity.ok().body(reportService.save(save));
        }
        return ResponseEntity.badRequest().body("Id not found!!");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        try {
            reportService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
