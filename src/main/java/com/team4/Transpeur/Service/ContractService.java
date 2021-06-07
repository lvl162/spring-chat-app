package com.team4.Transpeur.Service;


import com.team4.Transpeur.Model.Entities.Contract;

import java.util.List;
import java.util.Optional;

public interface ContractService {
    List<Contract> findAll();
    Optional<Contract> findById(Long id);
    void deleteById(Long id);
    Contract save(Contract contract);

    Boolean isRated(Long atContractId);
}
