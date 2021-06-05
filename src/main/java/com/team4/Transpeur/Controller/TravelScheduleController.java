package com.team4.Transpeur.Controller;

import com.team4.Transpeur.Model.DTO.SearchDTO;
import com.team4.Transpeur.Model.DTO.TravelScheduleDTO;
import com.team4.Transpeur.Model.Entities.TravelSchedule;
import com.team4.Transpeur.Model.Entities.User;
import com.team4.Transpeur.Model.DTO.Payload.Request.TravelScheduleRequest;
import com.team4.Transpeur.Model.DTO.Payload.Respone.MessageResponse;
import com.team4.Transpeur.Service.TravelScheduleService;
import com.team4.Transpeur.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sipios.springsearch.anotation.SearchSpec;
import org.thymeleaf.util.DateUtils;

import javax.validation.Valid;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTravelSchedule(@PathVariable("id") long id, @RequestBody TravelSchedule travelSchedule) {
        Optional<TravelSchedule> travelScheduleServiceById = travelScheduleService.findById(id);

        if (travelScheduleServiceById.isPresent()) {
            TravelSchedule _travelSchedule = travelScheduleServiceById.get();
            _travelSchedule.setDescription(travelSchedule.getDescription());
            _travelSchedule.setDescription(travelSchedule.getDescription());
            _travelSchedule.setFromPlace(travelSchedule.getFromPlace());
            return ResponseEntity.ok().body(travelScheduleService.save(_travelSchedule));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") Long id) {
        try {
            travelScheduleService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/new")
    public ResponseEntity<?> newTravel(@Valid @RequestBody TravelScheduleDTO travel) {
        Optional<User> creator = userService.findById(travel.getCreatorId());
        if (!creator.isPresent()) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Creator not found"));
        }
        TravelSchedule travelSchedule = new TravelSchedule(travel.getDescription(), travel.getFromPlace(), travel.getToPlace()
        , travel.getFromTime(), travel.getToTime(), creator.get(), travel.getTransport());

        travelScheduleService.save(travelSchedule);

        return ResponseEntity.ok(new MessageResponse("New travel schedule successfully posted"));
    }

    @GetMapping("/uid/{id}")
    public ResponseEntity<?> getPostsByUid(@PathVariable("id") Long id) {
            Set<TravelScheduleDTO> travelScheduleSet = userService.findById(id).get().getTravelSchedules()
                    .stream().map(m -> new TravelScheduleDTO(m)).collect(Collectors.toSet());
            if (travelScheduleSet != null)
            return ResponseEntity.ok().body(travelScheduleSet);
        return ResponseEntity.badRequest().body(new MessageResponse("Not found"));

    }
    @GetMapping("/uname/{uname}")
    public ResponseEntity<?> getPostsByUid(@PathVariable("uname") String uname) {
        Set<TravelScheduleDTO> travelScheduleSet = userService.findByUsername(uname).get().getTravelSchedules()
                .stream().map(m -> new TravelScheduleDTO(m)).collect(Collectors.toSet());
        if (travelScheduleSet != null)
            return ResponseEntity.ok().body(travelScheduleSet);
        return ResponseEntity.badRequest().body(new MessageResponse("Not found"));

    }
    @GetMapping("/all")
    public ResponseEntity<?> getAll(Pageable page) {
        return ResponseEntity.ok().body(travelScheduleService.findAll(page).map(m -> new TravelScheduleDTO(m)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Long id) {
        Optional<TravelSchedule> travel = travelScheduleService.findById(id);
        if (travel.isPresent()) {
            return ResponseEntity.ok().body(travel.get());
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Not found"));
    }

    @GetMapping("/travels")
    public ResponseEntity<?> searchForCars(@RequestBody Specification<TravelSchedule> specs) {
        List<TravelScheduleDTO> travelScheduleDTOS = travelScheduleService.findAll(Specification.where(specs)).stream().map(TravelScheduleDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(travelScheduleDTOS);
    }
    public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }
    private boolean match(TravelSchedule trs,SearchDTO searchDTO) {
        boolean isWord = false;
        boolean isFromPlace = false;
        boolean isToPlace = false;
        boolean isTransport = false;
        boolean isFromTime = false;
        boolean isToTime = false;

        if (searchDTO.getWord() != null) {
            isWord = trs.getUser().getUsername().contains(searchDTO.getWord());
        }
        if (searchDTO.getFromPlace() != null) {
            isFromPlace = trs.getFromPlace().contains(searchDTO.getFromPlace());
        }
        if (searchDTO.getToPlace() != null) {
            isToPlace = trs.getToPlace().contains(searchDTO.getToPlace());
        }
        if (searchDTO.getTransport() != null) {
            if (trs.getTransport() != null)
            isTransport = trs.getTransport().contains(searchDTO.getTransport());
        }
        if (searchDTO.getFromTime() != null) {
//            isFromTime = trs.getFromTime().equals(searchDTO.getFromTime());
            long diff = getDateDiff(trs.getFromTime(), searchDTO.getFromTime(), TimeUnit.HOURS);
            if (Math.abs(diff) < 12) {
                isFromTime = true;
            }
        }
        if (searchDTO.getToTime() != null) {
//            isToTime = trs.getToTime().equals(searchDTO.getToTime());
            long diff = getDateDiff(trs.getToTime(), searchDTO.getToTime(), TimeUnit.HOURS);
            if (Math.abs(diff) < 12) {
                isToTime = true;
            }
        }
        return isWord || isFromTime || isTransport || isToTime || isToPlace || isFromPlace;
    }
    @PostMapping("/search")
    public ResponseEntity<?> searchForCars(@Valid @RequestBody SearchDTO searchDTO) {
        List<TravelScheduleDTO> travelScheduleDTOS = travelScheduleService.findAll().stream()
                .filter(p -> match(p, searchDTO))
                .map(TravelScheduleDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(travelScheduleDTOS);
    }
}
