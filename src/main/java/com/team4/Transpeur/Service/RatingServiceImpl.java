package com.team4.Transpeur.Service;

import com.team4.Transpeur.Model.Entities.Contract;
import com.team4.Transpeur.Model.Entities.Rating;
import com.team4.Transpeur.Model.Entities.TravelSchedule;
import com.team4.Transpeur.Repositories.ContractRepository;
import com.team4.Transpeur.Repositories.RatingRepository;
import com.team4.Transpeur.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class RatingServiceImpl implements RatingService{
    final RatingRepository ratingRepository;
    final UserRepository userRepository;
    @Autowired
    public RatingServiceImpl(RatingRepository ratingRepository, UserRepository userRepository) {
        this.ratingRepository = ratingRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Rating> findAll() {
        return ratingRepository.findAll();
    }

    @Override
    public Rating save(Rating rating) {
        return ratingRepository.saveAndFlush(rating);
    }

    @Override
    public Float getAvgRatingByUid(Long id) {
        Integer sum = 0, num = 0;

        for (TravelSchedule t : userRepository.findById(id).get().getTravelSchedules()) {
            if (t.getContracts() != null)
            for (Contract c : t.getContracts()) {
                if (c.getRating() != null) {
                    sum= sum + c.getRating().getStar();
                    num++;
                }
            }
        }
        if (num == 0) return (float)0;
        return (float)sum/(float)num;
    }

    @Override
    public Optional<Rating> findById(Long id) {
        return ratingRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        ratingRepository.deleteById(id);
    }
}
