package com.team4.Transpeur.Service;

import com.team4.Transpeur.Model.Entities.Contract;
import com.team4.Transpeur.Repositories.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContractServiceImpl implements ContractService{
    final ContractRepository contractRepository;
    @Autowired
    public ContractServiceImpl(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    @Override
    public List<Contract> findAll() {
        return contractRepository.findAll();
    }

    @Override
    public Contract save(Contract contract) {
        return contractRepository.saveAndFlush(contract);
    }

    @Override
    public Optional<Contract> findById(Long id) {
        return contractRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        contractRepository.deleteById(id);
    }
}
