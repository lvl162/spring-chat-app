package com.team4.Transpeur.Service;

import com.team4.Transpeur.Model.Entities.TravelSchedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface TravelScheduleService {
    TravelSchedule save(TravelSchedule travelSchedule);
    List<TravelSchedule> findAll();
    List<TravelSchedule> findAll(Specification spec);

    Optional<TravelSchedule> findById(Long id);
    void deleteById(Long id);
    Page<TravelSchedule> findAll(Pageable pageable);

    void deactive(Long id);
}
