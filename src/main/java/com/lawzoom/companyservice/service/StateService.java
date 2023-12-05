package com.lawzoom.companyservice.service;

import com.lawzoom.companyservice.model.region.States;

import java.util.List;

public interface StateService {

    States createOrUpdateState(States states);

    List<States> getAllStates();

    States getStateById(Long id);


    States updateState(Long id, States states);


}
