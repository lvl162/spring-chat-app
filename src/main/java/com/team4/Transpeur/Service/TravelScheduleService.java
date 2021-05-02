package com.team4.Transpeur.Service;

import com.team4.Transpeur.Model.Entities.TravelSchedule;

import java.util.List;
import java.util.Optional;

public interface TravelScheduleService {
    public TravelSchedule save(TravelSchedule travelSchedule);
    public List<TravelSchedule> findAll();
    public Optional<TravelSchedule> findById(Long id);
}
