package com.lawzoom.companyservice.serviceImpl.companyLogic;

import com.lawzoom.companyservice.dto.companyDto.CompanyBusinessUnitDto;
import com.lawzoom.companyservice.dto.companyDto.CompanyRequest;
import com.lawzoom.companyservice.dto.companyDto.CompanyResponse;
import com.lawzoom.companyservice.dto.companyDto.TotalComplianceDto;
import com.lawzoom.companyservice.feignClient.AuthenticationFeignClient;
import com.lawzoom.companyservice.feignClient.ComplianceMap;
import com.lawzoom.companyservice.model.companyModel.Company;
import com.lawzoom.companyservice.model.companyModel.CompanyType;
import com.lawzoom.companyservice.model.teamMemberModel.TeamMember;
//import com.lawzoom.companyservice.model.teamModel.Team;
import com.lawzoom.companyservice.model.businessUnitModel.BusinessUnit;
import com.lawzoom.companyservice.dto.userDto.UserRequest;
import com.lawzoom.companyservice.repository.businessRepo.BusinessUnitRepository;
import com.lawzoom.companyservice.repository.companyRepo.CompanyRepository;
import com.lawzoom.companyservice.repository.companyRepo.CompanyTypeRepository;
import com.lawzoom.companyservice.repository.team.TeamMemberRepository;
//import com.lawzoom.companyservice.repository.team.TeamRepository;
import com.lawzoom.companyservice.services.companyService.CompanyService;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private  CompanyRepository companyRepository;

    @Autowired
   private   ComplianceMap complianceMap;

    @Autowired
    private CompanyTypeRepository companyTypeRepository;

    @Autowired
    private AuthenticationFeignClient authenticationFeignClient;

//    @Autowired
//    private TeamRepository teamRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private BusinessUnitRepository businessUnitRepository;

    @Autowired
    private TeamMemberRepository teamMemberRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }


//    @Override
//    public Map<String, Object> getCompanyByMemberMail(String memberMail) {
//        // Check if the email exists in TeamMember table
//        TeamMember teamMember = teamMemberRepository.findByMemberMail(memberMail);
//
//        if (teamMember != null) {
//            // If the TeamMember is found, retrieve the associated Team
//            Team team = teamMember.getTeam();
//
//            if (team != null) {
//                Long teamId = team.getId();
//
//                // Check if the Team with the retrieved teamId exists
//                Optional<Team> optionalTeam = teamRepository.findById(teamId);
//
//                if (optionalTeam.isPresent()) {
//                    // If the Team is found, retrieve the associated Company
//                    Company company = optionalTeam.get().getCompany();
//
//                    if (company != null) {
//                        // If the Company is found, return companyId, companyName, and Company data
//                        Map<String, Object> result = new HashMap<>();
//                        result.put("companyId", company.getId());
//                        result.put("companyName", company.getCompanyName());
//                        result.put("registrationDate",company.getRegistrationDate());
//                        return result;
//                    } else {
//                        // Handle the case where the Team doesn't have an associated Company
//                        return handleNotFoundError("Company not found for the given Team.");
//                    }
//                } else {
//                    // Handle the case where the Team is not found
//                    return handleNotFoundError("Team not found for the given Team ID.");
//                }
//            }
//        }
//
//        // Handle the case where the TeamMember is not found
//        return handleNotFoundError("TeamMember not found for the given email.");
//    }

    private Map<String, Object> handleNotFoundError(String message) {
        Map<String, Object> errorResult = new HashMap<>();
        errorResult.put("error", message);
        return errorResult;
    }


    @Override
    public CompanyResponse createCompany(CompanyRequest companyRequest , Long userId)
    {
        // checking user ID is exist or not in authentication service
        UserRequest userRequest = authenticationFeignClient.getUserId(userId);

        if (userRequest == null) {

            throw new NotFoundException("User not found with userId: " + userId);
        }

        Company company = new Company();

        company.setFirstName(companyRequest.getFirstName());
        company.setLastName(companyRequest.getLastName());

//        Optional<CompanyType> companySavedData = companyTypeRepository.
//                findById(companyRequest.getCompanyType());
//
//        company.setCompanyType(companySavedData.get());

        company.setCompanyType(companyRequest.getCompanyType());

        company.setCinNumber(companyRequest.getCompanyCINNumber());
        company.setBusinessActivityEmail(companyRequest.getBusinessActivityEmail());
        company.setCompanyName(companyRequest.getCompanyName());
//        company.setCity(companyRequest.getCompanyCity());
        company.setAddress(companyRequest.getCompanyAddress());
        company.setDesignation(companyRequest.getDesignation());
        company.setContractEmployee(companyRequest.getContractEmployee());
        company.setCreatedAt (new Date());
        company.setUpdatedAt (new Date());
        company.setTurnover(companyRequest.getCompanyTurnover());
        company.setGstNumber(companyRequest.getGstNumber());
//        company.setBusinessActivity(companyRequest.getBusinessActivity());
        company.setEnable(companyRequest.isEnable());
        company.setLocatedAt(companyRequest.getLocatedAt());
//        company.setState(companyRequest.getCompanyState());
        company.setPinCode(companyRequest.getCompanyPinCode());
        company.setPermanentEmployee(companyRequest.getPermanentEmployee());
        company.setRegistrationNumber(companyRequest.getCompanyRegistrationNumber());
        company.setRegistrationDate(companyRequest.getCompanyRegistrationDate());
        company.setCinNumber(companyRequest.getCompanyCINNumber());
        company.setRemarks(companyRequest.getCompanyRemarks());
        company.setOperationUnitAddress(companyRequest.getOperationUnitAddress());
        company.setTurnover(companyRequest.getCompanyTurnover());
        company.setUserId(userId);

        company = companyRepository.save(company);

        CompanyResponse companyResponse = new CompanyResponse();
//        companyResponse.setCompanyId(company.getId());
        companyResponse.setFirstName(company.getFirstName());
        companyResponse.setLastName(company.getLastName());
        companyResponse.setCompanyType(company.getCompanyType());
        companyResponse.setCompanyCINNumber(company.getCinNumber());
        companyResponse.setCompanyRegistrationNumber(company.getRegistrationNumber());
        companyResponse.setCompanyRegistrationDate(company.getRegistrationDate());
        companyResponse.setCompanyRemarks(company.getRemarks());
        companyResponse.setCompanyPinCode(company.getPinCode());
        companyResponse.setCompanyAddress(company.getAddress());
        companyResponse.setLocatedAt(company.getLocatedAt());
        companyResponse.setCreatedAt(company.getCreatedAt());
        companyResponse.setUpdatedAt(company.getUpdatedAt());
        companyResponse.setEnable(company.isEnable());
//        companyResponse.setBusinessActivity(company.getBusinessActivity());
        companyResponse.setPermanentEmployee(company.getPermanentEmployee());
        companyResponse.setContractEmployee(company.getContractEmployee());
        companyResponse.setGstNumber(company.getGstNumber());
        companyResponse.setCompanyName(company.getCompanyName());
        companyResponse.setBusinessActivityEmail(company.getBusinessActivityEmail());
        companyResponse.setDesignation(company.getDesignation());
//        companyResponse.setCompanyState(company.getState());
//        companyResponse.setCompanyCity(company.getCity());

        companyResponse.setCompanyType(company.getCompanyType());
        companyResponse.setCompanyRegistrationNumber(company.getRegistrationNumber());
        companyResponse.setCompanyRegistrationDate(company.getRegistrationDate());
        companyResponse.setCompanyCINNumber(company.getCinNumber());
        companyResponse.setCompanyRemarks(company.getRemarks());
        companyResponse.setUpdatedAt(company.getUpdatedAt());
        companyResponse.setOperationUnitAddress(company.getOperationUnitAddress());
        companyResponse.setCompanyType(companyResponse.getCompanyType());
        companyResponse.setUserId(userId);

//        updateIsAssociated(userId, true);



        return companyResponse;
    }

//    public void updateIsAssociated(Long userId, boolean isAssociated) {
//        authenticationFeignClient.updateIsAssociated(userId, isAssociated);
//    }


    @Override
    public List<CompanyResponse> getAllCompanies() {
        List<Company> companies = companyRepository.findAll();
        List<CompanyResponse> companyResponses = new ArrayList<>();

        for (Company company : companies) {
            CompanyResponse response = new CompanyResponse();

//            response.setCompanyId(company.getId());

            response.setCompanyType(company.getCompanyType());
            response.setCompanyName(company.getCompanyName());
            response.setFirstName(company.getFirstName());
            response.setLastName(company.getLastName());
            response.setBusinessActivityEmail(company.getBusinessActivityEmail());
            response.setDesignation(company.getDesignation());
//            response.setCompanyState(company.getState());
//            response.setCompanyCity(company.getCity());
            response.setCompanyRegistrationNumber(company.getRegistrationNumber());
            response.setCompanyRegistrationDate(company.getRegistrationDate());
            response.setCompanyCINNumber(company.getCinNumber());
            response.setCompanyRemarks(company.getRemarks());
            response.setCompanyPinCode(company.getPinCode());
            response.setCompanyAddress(company.getAddress());
            response.setCompanyTurnover(company.getTurnover());
            response.setLocatedAt(company.getLocatedAt());
            company.setCreatedAt (new Date());
            company.setUpdatedAt (new Date());
            response.setEnable(company.isEnable());
//            response.setBusinessActivity(company.getBusinessActivity());
            response.setPermanentEmployee(company.getPermanentEmployee());
            response.setContractEmployee(company.getContractEmployee());
            response.setGstNumber(company.getGstNumber());
            response.setOperationUnitAddress(company.getOperationUnitAddress());
//            response.setTeamsList(company.getTeams());


            companyResponses.add(response);
        }

        return companyResponses;
    }


    @Override
    public List<CompanyResponse> getCompaniesByUserId(Long userId) {
        List<Company> companies = companyRepository.findByUserId(userId);

        return companies.stream()
                .map(this::mapToCompanyResponse)
                .collect(Collectors.toList());
    }

    private CompanyResponse mapToCompanyResponse(Company company) {
        CompanyResponse companyResponse = new CompanyResponse();

//        companyResponse.setCompanyId(company.getId());
        companyResponse.setUserId(company.getUserId());
        companyResponse.setCompanyType(company.getCompanyType());
        companyResponse.setCompanyName(company.getCompanyName());
        companyResponse.setFirstName(company.getFirstName());
        companyResponse.setLastName(company.getLastName());
        companyResponse.setBusinessActivityEmail(company.getBusinessActivityEmail());
        companyResponse.setDesignation(company.getDesignation());
//        companyResponse.setCompanyState(company.getState());
//        companyResponse.setCompanyCity(company.getCity());
        companyResponse.setCompanyRegistrationNumber(company.getRegistrationNumber());
        companyResponse.setCompanyRegistrationDate(company.getRegistrationDate());
        companyResponse.setCompanyCINNumber(company.getCinNumber());
        companyResponse.setCompanyRemarks(company.getRemarks());
        companyResponse.setCompanyPinCode(company.getPinCode());
        companyResponse.setCompanyAddress(company.getAddress());
        companyResponse.setCompanyTurnover(company.getTurnover());
        companyResponse.setLocatedAt(company.getLocatedAt());
        companyResponse.setCreatedAt(company.getCreatedAt());
        companyResponse.setUpdatedAt(company.getUpdatedAt());
        companyResponse.setEnable(company.isEnable());
//        companyResponse.setBusinessActivity(company.getBusinessActivity());
        companyResponse.setPermanentEmployee(company.getPermanentEmployee());
        companyResponse.setContractEmployee(company.getContractEmployee());
        companyResponse.setGstNumber(company.getGstNumber());
        companyResponse.setOperationUnitAddress(company.getOperationUnitAddress());


        return companyResponse;
    }

    @Override
    public CompanyResponse updateCompany(CompanyRequest companyRequest, Long companyId)
    {
        Optional<Company> companyOptional = companyRepository.findById(companyId);

        if (companyOptional.isPresent()) {
            Company companyData = companyOptional.get();

            companyData.setCompanyName(companyRequest.getCompanyName());
            companyData.setAddress(companyRequest.getCompanyAddress());
//            companyData.setCompanyType(companyRequest.getCompanyType());
            companyData.setBusinessActivityEmail(companyRequest.getBusinessActivityEmail());
            companyData.setFirstName(companyRequest.getFirstName());
            companyData.setLastName(companyRequest.getLastName());
//            companyData.setState(companyRequest.getCompanyState());
//            companyData.setCity(companyRequest.getCompanyCity());
            companyData.setRegistrationNumber(companyRequest.getCompanyRegistrationNumber());
            companyData.setRegistrationDate(companyRequest.getCompanyRegistrationDate());
            companyData.setCinNumber(companyRequest.getCompanyCINNumber());
            companyData.setRemarks(companyRequest.getCompanyRemarks());
            companyData.setPinCode(companyRequest.getCompanyPinCode());
            companyData.setTurnover(companyRequest.getCompanyTurnover());
            companyData.setLocatedAt(companyRequest.getLocatedAt());
            companyData.setCreatedAt(companyRequest.getCreatedAt());
            companyData.setUpdatedAt(companyRequest.getUpdatedAt());
            companyData.setEnable(companyRequest.isEnable());
//            companyData.setBusinessActivity(companyRequest.getBusinessActivity());
            companyData.setPermanentEmployee(companyRequest.getPermanentEmployee());
            companyData.setContractEmployee(companyRequest.getContractEmployee());
            companyData.setGstNumber(companyRequest.getGstNumber());
            companyData.setOperationUnitAddress(companyRequest.getOperationUnitAddress());

            Company savedData = companyRepository.save(companyData);

            CompanyResponse companyResponse = new CompanyResponse();

//            companyResponse.setCompanyId(savedData.getId());
            companyResponse.setCompanyName(savedData.getCompanyName());
            companyResponse.setCompanyAddress(savedData.getAddress());
            companyResponse.setCompanyType(savedData.getCompanyType());
            companyResponse.setBusinessActivityEmail(savedData.getBusinessActivityEmail());
            companyResponse.setFirstName(savedData.getFirstName());
            companyResponse.setLastName(savedData.getLastName());
//            companyResponse.setCompanyState(savedData.getState());
//            companyResponse.setCompanyCity(savedData.getCity());
            companyResponse.setCompanyRegistrationNumber(savedData.getRegistrationNumber());
            companyResponse.setCompanyRegistrationDate(savedData.getRegistrationDate());
            companyResponse.setCompanyCINNumber(savedData.getCinNumber());
            companyResponse.setCompanyRemarks(savedData.getRemarks());
            companyResponse.setCompanyPinCode(savedData.getPinCode());
            companyResponse.setCompanyTurnover(savedData.getTurnover());
            companyResponse.setLocatedAt(savedData.getLocatedAt());
            companyResponse.setCreatedAt(savedData.getCreatedAt());
            companyResponse.setUpdatedAt(savedData.getUpdatedAt());
            companyResponse.setEnable(savedData.isEnable());
//            companyResponse.setBusinessActivity(savedData.getBusinessActivity());
            companyResponse.setPermanentEmployee(savedData.getPermanentEmployee());
            companyResponse.setContractEmployee(savedData.getContractEmployee());
            companyResponse.setGstNumber(savedData.getGstNumber());
            companyResponse.setOperationUnitAddress(savedData.getOperationUnitAddress());


            return companyResponse;
        }

        return null;
    }


    @Override
    public void deleteCompany(Long id) throws NotFoundException {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if (companyOptional.isPresent()) {
            Company company = companyOptional.get();
            companyRepository.delete(company);
        } else {
            throw new NotFoundException("Company with ID " + id + " not found");
        }
    }

//    @Override
//    public List<CompanyResponse> getAllCompaniesByUserId(Long userId) {
//        List<Company> companies = companyRepository.findAllByUserId(userId);
//        return companies.stream()
//                .map(company -> {
//                    CompanyResponse response = new CompanyResponse();
//                    response.setCompanyId(company.getId());
//                    response.setCompanyType(company.getCompanyType());
//                    response.setCompanyName(company.getCompanyName());
//                    response.setFirstName(company.getFirstName());
//                    response.setLastName(company.getLastName());
//                    response.setBusinessActivityEmail(company.getBusinessActivityEmail());
//                    response.setDesignation(company.getDesignation());
//                    response.setCompanyState(company.getState());
//                    response.setCompanyCity(company.getCity());
//                    response.setCompanyRegistrationNumber(company.getRegistrationNumber());
//                    response.setCompanyRegistrationDate(company.getRegistrationDate());
//                    response.setCompanyCINNumber(company.getCinNumber());
//                    response.setCompanyRemarks(company.getRemarks());
//                    response.setCompanyPinCode(company.getPinCode());
//                    response.setCompanyAddress(company.getAddress());
//                    response.setCompanyTurnover(company.getTurnover());
//                    response.setLocatedAt(company.getLocatedAt());
//                    response.setCreatedAt(company.getCreatedAt());
//                    response.setUpdatedAt(company.getUpdatedAt());
//                    response.setEnable(company.isEnable());
//                    response.setBusinessActivity(company.getBusinessActivity());
//                    response.setPermanentEmployee(company.getPermanentEmployee());
//                    response.setContractEmployee(company.getContractEmployee());
//                    response.setGstNumber(company.getGstNumber());
//                    response.setOperationUnitAddress(company.getOperationUnitAddress());
//                    return response;
//
//                })
//                .collect(Collectors.toList());
//    }


//    @Override
//    public String getCompanyNameById(Long id, Long userId) {
//        Company companyOptional = companyRepository.findByIdAndUserId(id, userId);
//        if (companyOptional!=null) {
//            return companyOptional.getCompanyName();
//        } else {
//            throw new NotFoundException("Company not found with ID: " + id + " and User ID: " + userId);
//        }
//    }

//    @Override
//    public List<String> getAllCompanyNames() {
//
//        List<Company> companies= companyRepository.findAll();
//
//        return companies.stream()
//                .map(Company ::getCompanyName)
//                .collect(Collectors.toList());
//
//    }

//    public List<String> getAllTeamNames() {
//        List<Team> teams = teamRepository.findAll();
//        return teams.stream()
//                .map(Team::getTeamName)
//                .collect(Collectors.toList());
//    }


//    @Override
//    public List<String> getAllBusinessUnits() {
//
//        List<BusinessUnit> businessUnits =  businessUnitRepository.findAll();
//
//        return businessUnits.stream()
//                .map(BusinessUnit :: getCity)
//                .collect(Collectors.toList());
//    }
//
//    public List<Map<String, Object>> getAllCompaniesDetails() {
//        List<Company> companies = companyRepository.findAll();
//        return companies.stream()
//                .map(company -> {
//                    Map<String, Object> companyMap = new HashMap<>();
//                    companyMap.put("id", company.getId());
//                    companyMap.put("name", company.getCompanyName());
//                    return companyMap;
//                })
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public List<Map<String, Object>> getAllBusinessUnitsDetails() {
//        return null;
//    }

    public List<Map<String, Object>> getAllCompanyDetailsV2() {
        List<Company> companies = companyRepository.findAll();
        Map<Long,Integer>compCount=complianceMap.getComplianceCount();
        System.out.println(compCount);

        List<Map<String,Object>>result = new ArrayList<>();
        for(Company c:companies){
            Map<String,Object>res = new HashMap<>();
            res.put("id",c.getId());
            res.put("name",c.getCompanyName());
            res.put("businessUnit",c.getBusinessUnits()!=null?c.getBusinessUnits().stream().map(i->i.getAddress()).collect(Collectors.toSet()) : "NA");
            res.put("businessActivity",c.getBusinessActivity());
//            res.put("team",c.getTeams());
            res.put("lastUpdatedDate",c.getUpdatedAt());
            res.put("complianceCount",compCount.get(c.getId()));
//            res.put("updatedBy",c);
            result.add(res);
        }
        return result;
    }

//    @Override
//    public List<Map<String, Object>> getAllCompanyDetails() {
//
//        List<Company> companies = companyRepository.findAll();
//
//        return companies.stream().map(company -> {
//            Map<String,Object> companyMap = new HashMap<>();
//            companyMap.put("id",company.getId());
//            companyMap.put("name",company.getCompanyName());
//            return companyMap;
//
//        }).collect(Collectors.toList());
//    }
//// i want companyId, teamId and business id so i can pass in this API write code for it
//    @Override
//    public List<Map<String, Object>> getAllBusinessDetails() {
//
//        List<BusinessUnit> businessUnitsList= businessUnitRepository.findAll();
//
//        return businessUnitsList.stream().map(businessUnit -> {
//            Map<String,Object> businessMap = new HashMap<>();
//            businessMap.put("id",businessUnit.getId());
//            businessMap.put("name",businessUnit.getCity());
//            businessMap.put("businessacitivity",businessUnit.getBusinessActivity());
//
//            return businessMap;
//
//        }).collect(Collectors.toList());
//    }
//
//    @Override
//    public List<Map<String, Object>> getAllTeamDetails() {
//
//        List<Team> teamList= teamRepository.findAll();
//
//        return teamList.stream().map(team -> {
//            Map<String,Object> teamMap = new HashMap<>();
//            teamMap.put("id",team.getId());
//            teamMap.put("name",team.getTeamName());
//            teamMap.put("teamType",team.getTeamType());
//
//            return teamMap;
//
//        }).collect(Collectors.toList());
//    }

    public List<CompanyBusinessUnitDto> getCompanyUnitComplianceDetails(Long userId) {

        List<Company> companies = companyRepository.findByUserId(userId);


        List<CompanyBusinessUnitDto> companyBusinessUnitDtos = new ArrayList<>();

        List<Map<String, Object>> totalCompliance = complianceMap.getComplianceCountPerCompanyAndBusinessUnit();

        for (Company company : companies) {
            for (BusinessUnit businessUnit : company.getBusinessUnits()) {
                CompanyBusinessUnitDto dto = new CompanyBusinessUnitDto();
                dto.setCompanyId(company.getId());
                dto.setCompanyName(company.getCompanyName());
                dto.setBusinessUnitId(businessUnit.getId());
                dto.setBusinessUnit(businessUnit.getBusinessActivity());
                dto.setAddress(businessUnit.getAddress());

                dto.setLastUpdated(businessUnit.getUpdatedAt());

                List<TotalComplianceDto> totalComplianceDtos = totalCompliance.stream()
                        .filter(map -> String.valueOf(company.getId()).equals(String.valueOf(map.get("companyId")))
                                && String.valueOf(businessUnit.getId()).equals(String.valueOf(map.get("businessUnitId"))))
                        .map(map -> {
                            TotalComplianceDto totalComplianceDto = new TotalComplianceDto();
                            totalComplianceDto.setTotalCompliance((int) map.get("complianceCount"));
                            return totalComplianceDto;
                        })
                        .collect(Collectors.toList());

                dto.setTotalCompliance(totalComplianceDtos);


                companyBusinessUnitDtos.add(dto);
            }
        }

        return companyBusinessUnitDtos;
    }


}



