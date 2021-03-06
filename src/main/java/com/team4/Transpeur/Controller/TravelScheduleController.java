package com.team4.Transpeur.Controller;

import com.team4.Transpeur.Model.DTO.SearchDTO;
import com.team4.Transpeur.Model.DTO.TravelScheduleDTO;
import com.team4.Transpeur.Model.Entities.TravelSchedule;
import com.team4.Transpeur.Model.Entities.User;

import com.team4.Transpeur.Model.DTO.Payload.Respone.MessageResponse;
import com.team4.Transpeur.Service.TravelScheduleService;
import com.team4.Transpeur.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
    public ResponseEntity<HttpStatus> deletePost(@PathVariable("id") Long id) {
        try {
            travelScheduleService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/deactive/{id}")
    public ResponseEntity<?> deativePost(@PathVariable("id") Long id) {
        try {
            if (travelScheduleService.findById(id).isPresent()) {
                travelScheduleService.deactive(id);
                return ResponseEntity.ok().body(new MessageResponse("X??a th??nh c??ng"));
            }
            return ResponseEntity.badRequest().body(new MessageResponse("L???i: Kh??ng t??m th???y post"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("L???i: Da co loi xay ra"));

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
        List<TravelScheduleDTO> toSort = new ArrayList<>();
        for (TravelSchedule m : userService.findById(id).get().getTravelSchedules()) {
            if (m.getActive()) {
                TravelScheduleDTO travelScheduleDTO = new TravelScheduleDTO(m);
                toSort.add(travelScheduleDTO);
            }
        }
        toSort.sort(new Comparator<TravelScheduleDTO>() {
            @Override
            public int compare(TravelScheduleDTO o1, TravelScheduleDTO o2) {
                return o1.getCreateAt().compareTo(o2.getCreateAt());
            }
        });
        List<TravelScheduleDTO> travelScheduleSet = new ArrayList<>();
        for (TravelScheduleDTO travelScheduleDTO : toSort) {
            travelScheduleSet.add(travelScheduleDTO);
        }
        if (travelScheduleSet != null)
            return ResponseEntity.ok().body(travelScheduleSet);
        return ResponseEntity.badRequest().body(new MessageResponse("Not found"));

    }
    @GetMapping("/uname/{uname}")
    public ResponseEntity<?> getPostsByUid(@PathVariable("uname") String uname) {
        List<TravelScheduleDTO> toSort = new ArrayList<>();
        for (TravelSchedule m : userService.findByUsername(uname).get().getTravelSchedules()) {
            if (m.getActive()) {
                TravelScheduleDTO travelScheduleDTO = new TravelScheduleDTO(m);
                toSort.add(travelScheduleDTO);
            }
        }
        toSort.sort(new Comparator<TravelScheduleDTO>() {
            @Override
            public int compare(TravelScheduleDTO o1, TravelScheduleDTO o2) {
                return o1.getCreateAt().compareTo(o2.getCreateAt());
            }
        });
        List<TravelScheduleDTO> travelScheduleSet = new ArrayList<>();
        for (TravelScheduleDTO travelScheduleDTO : toSort) {
            travelScheduleSet.add(travelScheduleDTO);
        }
        if (travelScheduleSet != null)
            return ResponseEntity.ok().body(travelScheduleSet);
        return ResponseEntity.badRequest().body(new MessageResponse("Not found"));

    }
    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok().body(travelScheduleService.findAll().stream()
                .filter(m -> m.getActive()).
                map(m -> new TravelScheduleDTO(m))
                .sorted(new Comparator<TravelScheduleDTO>() {
                    @Override
                    public int compare(TravelScheduleDTO o1, TravelScheduleDTO o2) {
                        return o1.getCreateAt().compareTo(o2.getCreateAt());
                    }
                }).collect(Collectors.toList()));
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
                .filter(m -> m.getActive())
                .map(TravelScheduleDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(travelScheduleDTOS);
    }

    @GetMapping("/setup")
    public String SetUp() {
        List<TravelSchedule> ls = travelScheduleService.findAll();
        for (TravelSchedule ts:
             ls) {
            ts.setActive(true);
            travelScheduleService.save(ts);
        }
        return "True";
    }
}
