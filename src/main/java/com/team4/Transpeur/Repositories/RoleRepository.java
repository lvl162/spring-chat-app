package com.team4.Transpeur.Repositories;

import com.team4.Transpeur.Entities.ERole;
import com.team4.Transpeur.Entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}