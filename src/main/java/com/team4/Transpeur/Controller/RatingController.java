package com.team4.Transpeur.Controller;

import com.team4.Transpeur.Model.DTO.RatingDTO;
import com.team4.Transpeur.Model.Entities.Contract;
import com.team4.Transpeur.Model.Entities.Rating;
import com.team4.Transpeur.Service.ContractService;
import com.team4.Transpeur.Service.RatingService;
import com.team4.Transpeur.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rating")
public class RatingController {

    final RatingService ratingService;
    final ContractService contractService;
    final UserService userService;
    @Autowired
    public RatingController(RatingService ratingService, ContractService contractService, UserService userService) {
        this.ratingService = ratingService;
        this.contractService = contractService;
        this.userService = userService;
    }

    @PostMapping("/new")
    public ResponseEntity<?> newRating(@RequestBody RatingDTO rating) {
        try {
            Contract thisCon = contractService.findById(rating.getAtContractId()).get();
            if (!thisCon.getReceiver().getId().equals(rating.getFromId())
                    || !thisCon.getTravelSchedule().getUser().getId().equals(rating.getToId()))  throw  new Exception("Sai lech thong tin");
            if (contractService.isRated(rating.getAtContractId())) {throw  new Exception("this contract had been rated");}
            Rating r = new Rating();
            r.setContract(thisCon);
            r.setDescription(rating.getDescription());
            r.setStar(rating.getStar());
            return ResponseEntity.ok().body(ratingService.save(r));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRating(@PathVariable("id") Long id) {
        Optional<Rating> r = ratingService.findById(id);
        return ResponseEntity.ok().body(new RatingDTO(r.get()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteRating(@PathVariable("id") Long id) {
        try {
            ratingService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/uid/avg/{id}")
    public ResponseEntity<?> getAvgRating(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(ratingService.getAvgRatingByUid(id));
    }

    @GetMapping("/uid/all/{id}")
    public ResponseEntity<?> getAllRatingOfUserById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(ratingService.getAllRatingsByUserId(id).
                stream().map(m -> new RatingDTO(m))
        .collect(Collectors.toList()));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok().body(ratingService.findAll()
                .stream().map(m -> new RatingDTO(m))
                .collect(Collectors.toList()));
    }
}
