package com.lawzoom.companyservice.serviceImpl;

import com.lawzoom.companyservice.model.region.States;
import com.lawzoom.companyservice.repository.StatesRepository;
import com.lawzoom.companyservice.service.StateService;
import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StateServiceImpl implements StateService {

    @Autowired
    private StatesRepository statesRepository;

    @Override
    public List<States> getAllStates() {
        return statesRepository.findByIsEnable(true);
    }

//    @Override
//    public States getStateById(Long id) {
//        return statesRepository.findById(id).orElse(null);
//    }

//    @Override
//    public States createOrUpdateState(States states) {
//        return statesRepository.save(states);
//    }

    @Override
    public Object createOrUpdateStates(List<String> stateNames) {
        List<States> statesList = new ArrayList<>();
        List<String> existingStates = new ArrayList<>();

        for (String stateName : stateNames) {
            States existingState = statesRepository.findByStateName(stateName);
            if (existingState == null) {
                States states = new States();
                states.setStateName(stateName);
                states.setEnable(true);
                statesList.add(states);
            } else {
                System.out.println("State with name '" + stateName + "' already exists. Skipping...");
                existingStates.add(stateName);
            }
        }

        if (existingStates.isEmpty()) {
            List<States> savedStates = statesRepository.saveAll(statesList);
            return new ResponseEntity<>(savedStates, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Already Exist", HttpStatus.ALREADY_REPORTED);
        }
    }


    @Override
    public void deleteState(Long id) {
        statesRepository.deleteById(id);
    }

}
