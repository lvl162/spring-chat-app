package com.team4.Transpeur.Controller;

import com.team4.Transpeur.Model.Entities.TravelSchedule;
import com.team4.Transpeur.Model.Entities.User;
import com.team4.Transpeur.Model.BO.Payload.Request.TravelScheduleRequest;
import com.team4.Transpeur.Model.BO.Payload.Respone.MessageResponse;
import com.team4.Transpeur.Service.TravelScheduleService;
import com.team4.Transpeur.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/travel")
public class TravelScheduleController {
    final TravelScheduleService travelScheduleService;
    final UserService userService;

    @Autowired
    public TravelScheduleController(TravelScheduleService travelScheduleService, UserService userService) {
        this.travelScheduleService = travelScheduleService;
        this.userService = userService;
    }

    @PostMapping("/new")
    public ResponseEntity<?> newTravel(@Valid @RequestBody TravelScheduleRequest travel) {
        Optional<User> creator = userService.findById(travel.getCreatorId());
        if (!creator.isPresent()) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Creator not found"));
        }
        TravelSchedule travelSchedule = new TravelSchedule(travel.getDescription(), travel.getFromPlace(), travel.getToPlace()
        , travel.getFromTime(), travel.getToTime(), creator.get());

        travelScheduleService.save(travelSchedule);

        return ResponseEntity.ok(new MessageResponse("New travel schedule successfully posted"));
    }

    @GetMapping("/all")
    public List<TravelSchedule> getAll() {
        return travelScheduleService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Long id) {
        Optional<TravelSchedule> travel = travelScheduleService.findById(id);
        if (travel.isPresent()) {
            return ResponseEntity.ok().body(travel.get());
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Not found"));
    }
}
