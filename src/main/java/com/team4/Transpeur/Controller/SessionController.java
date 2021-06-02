package com.team4.Transpeur.Controller;

import com.team4.Transpeur.Security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SessionController {
    @Autowired
    private SessionRegistry sessionRegistry;

    @GetMapping("/api/sessions")
    @ResponseBody
    public List<SessionInformation> sessions(Authentication authentication, ModelMap model) {
        UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
        System.out.println(user.getId());
        List<SessionInformation> sessions = sessionRegistry.getAllSessions(authentication.getPrincipal(),
                true);
        model.put("sessions", sessions);
        return sessions;
    }
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
    @GetMapping("/api/actives_2")
    @ResponseBody
    public List<String> getUsersFromSessionRegistry() {
        return sessionRegistry.getAllPrincipals().stream()
                .filter(u -> !sessionRegistry.getAllSessions(u, false).isEmpty())
                .map(Object::toString)
                .collect(Collectors.toList());
    }
}
