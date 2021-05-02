package com.team4.Transpeur.Service;

import com.team4.Transpeur.Model.Entities.ERole;
import com.team4.Transpeur.Model.Entities.Role;
import com.team4.Transpeur.Repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class RoleServiceImpl implements RoleService {
    final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

  @Override
    public Optional<Role> findByName(ERole name) {
        return roleRepository.findByName(name);
    }
}
