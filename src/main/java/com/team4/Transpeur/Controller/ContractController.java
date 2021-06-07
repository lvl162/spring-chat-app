package com.team4.Transpeur.Controller;

import com.team4.Transpeur.Model.DTO.ContractDTO;
import com.team4.Transpeur.Model.Entities.Contract;
import com.team4.Transpeur.Model.Entities.TravelSchedule;
import com.team4.Transpeur.Model.Entities.User;
import com.team4.Transpeur.Service.ContractService;
import com.team4.Transpeur.Service.TravelScheduleService;
import com.team4.Transpeur.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/contract")
public class ContractController {
    final ContractService contractService;
    final UserService userService;
    final TravelScheduleService travelScheduleService;

    @Autowired
    public ContractController(ContractService contractService, UserService userService, TravelScheduleService travelScheduleService) {
        this.contractService = contractService;
        this.userService = userService;
        this.travelScheduleService = travelScheduleService;
    }

    @PostMapping("/new")
    public ResponseEntity<?> makeContract(@RequestBody ContractDTO contractDTO) {
        try {
            Contract contract = new Contract();
            contract.setPrice(contractDTO.getPrice());
            contract.setDescription(contractDTO.getDescription());
            contract.setTravelSchedule(travelScheduleService.findById(contractDTO.getTravelScheduleId()).get());
            contract.setReceiver(userService.findById(contractDTO.getReceiverId()).get());
            contractService.save(contract);
            return ResponseEntity.ok().body(contractDTO);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Error has been occurred");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok().body(contractService.findAll().stream().map(m -> new ContractDTO(m)).collect(Collectors.toList()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        if (contractService.findById(id).isPresent()) {
            contractService.deleteById(id);
            return ResponseEntity.ok().body("Successfully");
        }
        return ResponseEntity.badRequest().body("ID not found");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        Optional<Contract> contractOptional = contractService.findById(id);
        if (contractOptional.isPresent()) {

            return ResponseEntity.ok().body(new ContractDTO(contractOptional.get()));
        }
        return ResponseEntity.badRequest().body("ID not found");
    }
    @GetMapping("/uid/{id}")
    public ResponseEntity<?> getByUId(@PathVariable("id") Long id) {
        Optional<User> user = userService.findById(id);
        if (user.isPresent()) {

            return ResponseEntity.ok().body(user.get().getTravelSchedules().stream().map(m -> m.getContracts()
                    .stream()
            .map(c -> new ContractDTO(c)).collect(Collectors.toList())
            ).collect(Collectors.toList()));
        }
        return ResponseEntity.badRequest().body("User ID not found");
    }
    @GetMapping("/uname/{uname}")
    public ResponseEntity<?> getByUId(@PathVariable("uname") String uname) {
        Optional<User> user = userService.findByUsername(uname);
        if (user.isPresent()) {

            return ResponseEntity.ok().body(user.get().getTravelSchedules().stream().map(m -> m.getContracts()
                    .stream()
                    .map(c -> new ContractDTO(c)).collect(Collectors.toList())
            ).collect(Collectors.toList()));
        }
        return ResponseEntity.badRequest().body("UserName not found");
    }
}
