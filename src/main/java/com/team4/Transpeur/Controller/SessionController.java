package com.team4.Transpeur.Controller;

import com.team4.Transpeur.Security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
@RestController
public class SessionController {
    @Autowired
    private SessionRegistry sessionRegistry;

    @GetMapping("/api/actives")
    @ResponseBody
    public List<Long> getUser() {
        List<Object> principals = sessionRegistry.getAllPrincipals();

        List<Long> usersNamesList = new ArrayList<Long>();

        for (Object principal : principals) {
            if (principal instanceof UserDetailsImpl) {
                usersNamesList.add(((UserDetailsImpl) principal).getId());
            }
        }
        return usersNamesList;
    }
}
