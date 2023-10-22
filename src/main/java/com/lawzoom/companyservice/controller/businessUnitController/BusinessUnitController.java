package com.lawzoom.companyservice.controller.businessUnitController;



import com.lawzoom.companyservice.dto.businessUnitDto.BusinessUnitRequest;
import com.lawzoom.companyservice.dto.businessUnitDto.BusinessUnitResponse;
import com.lawzoom.companyservice.service.BusinessUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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





}
