package com.lawzoom.companyservice.service;

import com.lawzoom.companyservice.model.region.States;

import java.util.List;

public interface StateService {
    List<States> getAllStates();

//    States getStateById(Long id);

//    States createOrUpdateState(States states);

    void deleteState(Long id);

//    List<States> createOrUpdateStates(List<String> stateNames);
    Object createOrUpdateStates(List<String> stateNames);

}
