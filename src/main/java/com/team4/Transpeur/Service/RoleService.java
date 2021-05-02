package com.team4.Transpeur.Service;

import com.team4.Transpeur.Model.Entities.ERole;
import com.team4.Transpeur.Model.Entities.Role;

import java.util.Optional;
public interface RoleService {
    Optional<Role> findByName(ERole name);
}
