package com.lawzoom.companyservice.controller;

import com.lawzoom.companyservice.dto.AccessTypeDto;
import com.lawzoom.companyservice.services.AccessTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/companyServices/company")
public class AccessTypeController {

    @Autowired
    private AccessTypeService accessTypeService;

    @PostMapping("/createAccessType")
    public ResponseEntity<AccessTypeDto> createAccessType(@Valid @RequestBody AccessTypeDto accessTypeDto) {
        AccessTypeDto createdAccessType = accessTypeService.createAccessType(accessTypeDto);
        return new ResponseEntity<>(createdAccessType, HttpStatus.CREATED);
    }

    @GetMapping("/getAllAccessTypes")
    public ResponseEntity<List<AccessTypeDto>> getAllAccessTypes() {
        List<AccessTypeDto> accessTypes = accessTypeService.getAllAccessTypes();
        return ResponseEntity.ok(accessTypes);
    }


}
