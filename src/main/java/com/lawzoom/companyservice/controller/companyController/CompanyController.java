package com.lawzoom.companyservice.controller.companyController;

import com.lawzoom.companyservice.dto.companyDto.CompanyResponse;
import com.lawzoom.companyservice.service.CompanyService;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.lawzoom.companyservice.dto.companyDto.CompanyRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin
@RestController
//@RequestMapping("/companyServices/company")
@RequestMapping("/companyServices/company")

public class
CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping("/addCompany")
    public ResponseEntity<CompanyResponse> createCompany(@RequestBody CompanyRequest companyRequest , @RequestParam Long userId) {
        CompanyResponse companyResponse = companyService.createCompany(companyRequest,userId);
        return new ResponseEntity<>(companyResponse, HttpStatus.CREATED);
    }

    @GetMapping("/allCompany")
    public ResponseEntity<List<CompanyResponse>> getAllCompanies() {
        List<CompanyResponse> companies = companyService.getAllCompanies();
        return ResponseEntity.ok(companies);
    }
    @GetMapping("/fetchCompany")
    public CompanyResponse getCompany(@RequestParam Long id, @RequestParam Long userId) {
        return companyService.getCompanyById(id,userId);
    }

     @PutMapping("/updateCompany")
    public CompanyResponse updateCompany(@RequestBody CompanyRequest companyRequest,@RequestParam Long companyId) {
        return companyService.updateCompany(companyRequest,companyId);
    }

    @DeleteMapping("/removeCompany")
    public ResponseEntity<String> deleteCompany(@RequestParam Long id) {
        try {
            companyService.deleteCompany(id);

            return ResponseEntity.ok("Company with ID  deleted successfully");

        } catch (NotFoundException e) {

            return ResponseEntity.notFound().build();

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error while deleting company with ID " + id);
        }
    }

//    @GetMapping("/getAllCompanyNames")
//    public ResponseEntity<List<String>> getAllCompanyNames() {
//        List<String> companyNames = companyService.getAllCompanyNames();
//        return ResponseEntity.ok(companyNames);
//    }
//
//    @GetMapping("/details")
//    public Map<String, List<Map<String, Object>>> getAllDetails() {
//        Map<String, List<Map<String, Object>>> allDetails = new HashMap<>();
//
//        List<Map<String, Object>> companies = companyService.getAllCompaniesDetails();
//        allDetails.put("companies", companies);
//
//        List<Map<String,Object>> businessUnitList = companyService.getAllBusinessUnitsDetails();
//        allDetails.put("businessUnit",businessUnitList);
//
//        return allDetails;
//    }

    @GetMapping("getAllCompanyUnitTeamData")
    public Map<String,List<Map<String,Object>>> getAllDetailsOfCompanyUnitTeam()
    {

        Map<String,List<Map<String,Object>>> allDetails= new HashMap<>();

        List<Map<String,Object>> companyDetails =  companyService.getAllCompanyDetails();
        allDetails.put("companies",companyDetails);

        List<Map<String,Object>> businessDetails = companyService.getAllBusinessDetails();
        allDetails.put("businessUnit",businessDetails);

        List<Map<String,Object>> teams = companyService.getAllTeamDetails();
        allDetails.put("team",teams);

        return allDetails;



    }


}
