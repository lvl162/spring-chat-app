package com.team4.Transpeur.Controller;

import com.team4.Transpeur.Model.DTO.RatingDTO;
import com.team4.Transpeur.Model.Entities.Rating;
import com.team4.Transpeur.Service.ContractService;
import com.team4.Transpeur.Service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/rating")
public class RatingController {

    final RatingService ratingService;
    final ContractService contractService;

    @Autowired
    public RatingController(RatingService ratingService, ContractService contractService) {
        this.ratingService = ratingService;
        this.contractService = contractService;
    }

    @PostMapping("/new")
    public ResponseEntity<?> newRating(@RequestBody RatingDTO rating) {
        try {
            Rating r = new Rating();
            r.setContract(contractService.findById(rating.getAtContractId()).get());
            r.setDescription(r.getDescription());
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

    @GetMapping("/uid/{id}")
    public ResponseEntity<?> getAvgRating(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(ratingService.getAvgRatingByUid(id));
    }
}
