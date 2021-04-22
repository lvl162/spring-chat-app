package com.team4.Transpeur.Service;

import com.team4.Transpeur.Entities.ERole;
import com.team4.Transpeur.Entities.Role;
import org.springframework.stereotype.Service;

import java.util.Optional;
public interface RoleService {
    Optional<Role> findByName(ERole name);
}
