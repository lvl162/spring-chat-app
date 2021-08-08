package com.team4.Transpeur.Repositories;

import com.team4.Transpeur.Model.Entities.UserInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInformationRepository extends JpaRepository<UserInformation, Long> {
}
