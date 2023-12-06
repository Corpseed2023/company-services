package com.lawzoom.companyservice.services.regionService;

import com.lawzoom.companyservice.model.region.States;

import java.util.List;

public interface StateService {

    States createStates(States states);

    List<States> getAllStates();



    States updateState(Long id, States states);


}
