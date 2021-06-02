package com.team4.Transpeur.Service;

import com.team4.Transpeur.Model.Entities.UserInformation;

import java.util.Optional;

public interface UserInformationService {
    UserInformation save(UserInformation informationAuthentication);
    Optional<UserInformation> findUserInformationById(Long id);
}
