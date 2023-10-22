package com.lawzoom.companyservice.controller.businessUnitController;



import com.lawzoom.companyservice.dto.businessUnitDto.BusinessUnitRequest;
import com.lawzoom.companyservice.dto.businessUnitDto.BusinessUnitResponse;
import com.lawzoom.companyservice.service.BusinessUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/company/business-unit/{gstId}")
public class BusinessUnitController {



    @Autowired
    private BusinessUnitService businessUnitService;

    @PostMapping("/saveBusinessUnit")
    public ResponseEntity<BusinessUnitResponse> createBusinessUnit(@RequestBody BusinessUnitRequest businessUnitRequest,Long gstId)

    {
        try{
            BusinessUnitResponse savedBusinessData= businessUnitService.createBusinessUnit(businessUnitRequest,gstId);
            return new ResponseEntity<>(savedBusinessData, HttpStatus.CREATED);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @PutMapping("/updateBusinessUnit/{id}")
    public ResponseEntity<BusinessUnitResponse> updateBusinessUnit(
            @PathVariable Long gstId,
            @PathVariable Long businessUnitId,
            @RequestBody BusinessUnitRequest businessUnitRequest)

    {
        try {
            BusinessUnitResponse updatedBusinessData = businessUnitService.updateBusinessUnit(gstId, businessUnitId, businessUnitRequest);
            return new ResponseEntity<>(updatedBusinessData, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Add an endpoint to retrieve a single business unit by its ID
    @GetMapping("/getBusinessUnit/{businessUnitId}")
    public ResponseEntity<BusinessUnitResponse> getBusinessUnit(
            @PathVariable Long gstId,
            @PathVariable Long businessUnitId) {
        BusinessUnitResponse businessUnit = businessUnitService.getBusinessUnit(gstId, businessUnitId);
        return ResponseEntity.ok(businessUnit);
    }

    // Add an endpoint to retrieve all business units
    @GetMapping("/getAllBusinessUnits")
    public ResponseEntity<List<BusinessUnitResponse>> getAllBusinessUnits(@PathVariable Long gstId) {
        List<BusinessUnitResponse> businessUnits = businessUnitService.getAllBusinessUnits(gstId);
        return ResponseEntity.ok(businessUnits);
    }

    @DeleteMapping("/deleteBusinessUnit/{businessUnitId}")
    public ResponseEntity<String> deleteBusinessUnit(@PathVariable Long gstId, @PathVariable Long businessUnitId) {
        businessUnitService.deleteBusinessUnit(gstId, businessUnitId);
        return new ResponseEntity<>("Business unit deleted successfully", HttpStatus.OK);
    }
}
