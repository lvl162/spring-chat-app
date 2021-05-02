package com.team4.Transpeur.Service;

import com.team4.Transpeur.Model.Entities.TravelSchedule;
import com.team4.Transpeur.Repositories.TravelScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TravelScheduleServiceImpl implements TravelScheduleService{
    final TravelScheduleRepository travelScheduleRepository;

    @Autowired
    public TravelScheduleServiceImpl(TravelScheduleRepository travelScheduleRepository) {
        this.travelScheduleRepository = travelScheduleRepository;
    }

    @Override
    public TravelSchedule save(TravelSchedule travelSchedule) {
        return travelScheduleRepository.saveAndFlush(travelSchedule);
    }

    @Override
    public List<TravelSchedule> findAll() {
        return travelScheduleRepository.findAll();
    }

    @Override
    public Optional<TravelSchedule> findById(Long id) {
        return travelScheduleRepository.findById(id);
    }
}
