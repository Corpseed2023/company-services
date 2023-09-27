package com.lawzoom.companyservice.controller.companyController;

import com.lawzoom.companyservice.dto.companyDto.CompanyResponse;
import com.lawzoom.companyservice.exception.CompanyNotFoundException;
import com.lawzoom.companyservice.model.companyModel.Company;
import com.lawzoom.companyservice.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.lawzoom.companyservice.dto.companyDto.CompanyRequest;

import java.util.List;


@CrossOrigin
@RestController
//@RequestMapping("/companyServices/company")
@RequestMapping("/companyServices/company")

public class CompanyController {

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

        } catch (CompanyNotFoundException e) {

            return ResponseEntity.notFound().build();

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error while deleting company with ID " + id);
        }
    }
}
