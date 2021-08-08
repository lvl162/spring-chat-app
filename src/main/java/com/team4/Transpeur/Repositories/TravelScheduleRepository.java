package com.team4.Transpeur.Repositories;

import com.team4.Transpeur.Model.Entities.TravelSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelScheduleRepository extends JpaRepository<TravelSchedule, Long>, JpaSpecificationExecutor<TravelSchedule> {

}
