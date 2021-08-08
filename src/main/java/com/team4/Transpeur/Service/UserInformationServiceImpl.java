package com.team4.Transpeur.Service;

import com.team4.Transpeur.Model.Entities.UserInformation;
import com.team4.Transpeur.Repositories.UserInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInformationServiceImpl implements UserInformationService {
    final UserInformationRepository userInformationRepository;

    @Autowired
    public UserInformationServiceImpl(UserInformationRepository userInformationRepository) {
        this.userInformationRepository = userInformationRepository;
    }
    @Override
    public UserInformation save(UserInformation userInformation) {
        return userInformationRepository.saveAndFlush(userInformation);
    }

    @Override
    public Optional<UserInformation> findUserInformationById(Long id) {
        return userInformationRepository.findById(id);
    }
}
