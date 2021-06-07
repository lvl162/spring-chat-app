package com.team4.Transpeur.Service;

import com.team4.Transpeur.Model.Entities.Rating;

import java.util.List;
import java.util.Optional;

public interface RatingService {
    List<Rating> findAll();
    Optional<Rating> findById(Long id);
    void deleteById(Long id);
    Rating save(Rating rating);
    List<Rating> getAllRatingsByUserId(Long id);
    Float getAvgRatingByUid(Long id);
}
