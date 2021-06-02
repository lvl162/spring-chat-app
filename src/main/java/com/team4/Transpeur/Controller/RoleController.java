package com.team4.Transpeur.Controller;

import com.team4.Transpeur.Model.Entities.Role;
import com.team4.Transpeur.Repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
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