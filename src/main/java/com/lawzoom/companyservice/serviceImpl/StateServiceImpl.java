package com.lawzoom.companyservice.serviceImpl;

import com.lawzoom.companyservice.model.region.States;
import com.lawzoom.companyservice.repository.StatesRepository;
import com.lawzoom.companyservice.service.StateService;
import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StateServiceImpl implements StateService {

    @Autowired
    private StatesRepository statesRepository;

    @Override
    public List<States> getAllStates() {
        return statesRepository.findAll();
    }

    @Override
    public States getStateById(Long id) {
        return statesRepository.findById(id).orElse(null);
    }

    @Override
    public States createOrUpdateState(States states) {
        return statesRepository.save(states);
    }

    @Override
    public List<States> createOrUpdateStates(List<String> stateNames) {
        List<States> statesList = new ArrayList<>();
        for (String stateName : stateNames) {
            States states = new States();
            states.setStateName(stateName);
            statesList.add(states);
        }
        return statesRepository.saveAll(statesList);
    }

    @Override
    public void deleteState(Long id) {
        statesRepository.deleteById(id);
    }

}
