package com.team4.Transpeur.Controller;

import com.team4.Transpeur.Entities.Role;
import com.team4.Transpeur.Repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class RoleController {
    @Autowired
    private RoleRepository roleRepository;
    @GetMapping("/roles")
    public Page<Role> getRoles(Pageable pageable) {

        return roleRepository.findAll(pageable);
    }
    @PostMapping("/roles")
    public Role createRole(@Valid @RequestBody Role role) {
        return roleRepository.save(role);
    }
}