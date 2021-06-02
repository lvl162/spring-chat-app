package com.team4.Transpeur.Repositories;

import com.team4.Transpeur.Model.Entities.TravelSchedule;
import com.team4.Transpeur.Model.Entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelScheduleRepository extends JpaRepository<TravelSchedule, Long> {

}
