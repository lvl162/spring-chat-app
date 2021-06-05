package com.team4.Transpeur.Service;

import com.team4.Transpeur.Model.Entities.TravelSchedule;
import com.team4.Transpeur.Repositories.TravelScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TravelScheduleServiceImpl implements TravelScheduleService{
    final TravelScheduleRepository travelScheduleRepository;

    @Override
    public List<TravelSchedule> findAll(Specification spec) {
        return travelScheduleRepository.findAll(Specification.where(spec));
    }

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

    @Override
    public void deleteById(Long id) {
        travelScheduleRepository.deleteById(id);
    }

    @Override
    public Page<TravelSchedule> findAll(Pageable pageable) {
        return travelScheduleRepository.findAll(pageable);
    }
}
