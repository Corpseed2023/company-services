package com.lawzoom.companyservice.controller.regionController;

import com.lawzoom.companyservice.model.region.States;
import com.lawzoom.companyservice.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companyServices/company/states")
public class StatesController {

    @Autowired
    private StateService stateService;

    @GetMapping
    public ResponseEntity<List<States>> getAllStates() {
        List<States> statesList = stateService.getAllStates();
        return new ResponseEntity<>(statesList, HttpStatus.OK);
    }

    @GetMapping("/getState")
    public ResponseEntity<States> getStateById(@PathVariable Long id) {
        States states = stateService.getStateById(id);
        return new ResponseEntity<>(states, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<States> createOrUpdateState(@RequestBody States states) {
        States savedState = stateService.createOrUpdateState(states);
        return new ResponseEntity<>(savedState, HttpStatus.CREATED);
    }

    @PostMapping("/batch")
    public ResponseEntity<List<States>> createOrUpdateStates(@RequestBody List<String> stateNames) {
        List<States> savedStates = stateService.createOrUpdateStates(stateNames);
        return new ResponseEntity<>(savedStates, HttpStatus.CREATED);
    }

    @DeleteMapping("/removeState")
    public ResponseEntity<Void> deleteState(@PathVariable Long id) {
        stateService.deleteState(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}