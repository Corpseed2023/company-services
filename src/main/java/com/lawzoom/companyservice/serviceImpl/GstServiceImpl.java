//package com.lawzoom.companyservice.serviceImpl;
//
//
//import com.lawzoom.companyservice.dto.gstDto.GstRequest;
//import com.lawzoom.companyservice.dto.gstDto.GstResponse;
//import com.lawzoom.companyservice.exception.CompanyNotFoundException;
//import com.lawzoom.companyservice.model.businessUnitModel.BusinessUnit;
//import com.lawzoom.companyservice.model.companyModel.Company;
//import com.lawzoom.companyservice.model.gstModel.Gst;
//import com.lawzoom.companyservice.repository.BusinessUnitRepository;
//import com.lawzoom.companyservice.repository.CompanyRepository;
//import com.lawzoom.companyservice.repository.GstRepository;
//import com.lawzoom.companyservice.service.GstService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class GstServiceImpl implements GstService {
//
//    @Autowired
//    private GstRepository gstRepository;
//
//    @Autowired
//    private CompanyRepository companyRepository;
//
//    @Autowired
//    private BusinessUnitRepository businessUnitRepository;
//
//    @Override
//    public GstResponse createGst(GstRequest gstRequest, Long businessUnitId) {
//
//        // Check if the company with the given companyId exists
//        BusinessUnit businessUnit = businessUnitRepository.findById(businessUnitId)
//                .orElseThrow(() -> new CompanyNotFoundException("Business Unit not found for ID: " + businessUnitId));
//
//        Gst gst = new Gst();
//        gst.setGstNumber(gstRequest.getGstNumber());
//        gst.setStateJurisdiction(gstRequest.getStateJurisdiction());
//        gst.setRegistrationDate(gstRequest.getRegistrationDate()); // Set the registration date
//        gst.setCreatedAt(gstRequest.getCreatedAt()); // Set the created date
//        gst.setUpdatedAt(gstRequest.getUpdatedAt()); // Set the updated date
//        gst.setEnable(gstRequest.isEnable()); // Set isEnable field
//
//        // Save the Gst entity
//        Gst savedGst = gstRepository.save(gst);
//
//        GstResponse response = new GstResponse();
//        response.setId(savedGst.getId());
//        response.setGstNumber(savedGst.getGstNumber());
//        response.setStateJurisdiction(savedGst.getStateJurisdiction());
//        response.setRegistrationDate(savedGst.getRegistrationDate());
//        response.setCreatedAt(savedGst.getCreatedAt());
//        response.setUpdatedAt(savedGst.getUpdatedAt());
//        response.setEnable(savedGst.isEnable());
//
//
//        return response;
//    }
//
//
//    @Override
//    public GstResponse updateGst(Long companyId, Long gstId, GstRequest gstRequest) {
//        // Fetch the existing Gst entity from the database
//        Gst existingGst = gstRepository.findById(gstId)
//                .orElseThrow(() -> new RuntimeException("GST not found with id: " + gstId));
//
//        // Update the existing Gst entity with the new values
//        existingGst.setGstNumber(gstRequest.getGstNumber());
//        existingGst.setStateJurisdiction(gstRequest.getStateJurisdiction());
//        // Update other properties as needed...
//
//        // Set the updatedAt property to the current date and time
//        existingGst.setUpdatedAt(new Date());
//
//        // Save the updated entity back to the repository
//        Gst updatedGst = gstRepository.save(existingGst);
//
//        // Map the updated entity to DTO for response
//        GstResponse response = new GstResponse();
//        response.setId(updatedGst.getId());
//        response.setGstNumber(updatedGst.getGstNumber());
//        response.setStateJurisdiction(updatedGst.getStateJurisdiction());
//        response.setRegistrationDate(updatedGst.getRegistrationDate());
//        response.setCreatedAt(updatedGst.getCreatedAt());
//        response.setUpdatedAt(updatedGst.getUpdatedAt());
//        response.setEnable(updatedGst.isEnable());
//
//        return response;
//    }
//
//    @Override
//    public List<GstResponse> getAllGst(Long companyId) {
//
//        Optional<Company> companyData = companyRepository.findById(companyId);
//
//        // Retrieve a list of GST entities by company ID from the repository
//
//
//        if (companyData.isPresent()) {
//            // Company data exists, proceed to retrieve and process GST entities
//
//            // Retrieve a list of GST entities by company ID from the repository
//            List<Gst> gstList = gstRepository.findByCompanyId(companyId);
//
//            // Create a list to store GstResponse objects
//            List<GstResponse> gstResponseList = new ArrayList<>();
//
//            // Iterate through the retrieved GST entities and create GstResponse objects
//            for (Gst gst : gstList) {
//                GstResponse gstResponse = new GstResponse();
//                gstResponse.setId(gst.getId());
//                gstResponse.setGstNumber(gst.getGstNumber());
//                gstResponse.setStateJurisdiction(gst.getStateJurisdiction());
//                gstResponse.setRegistrationDate(gst.getRegistrationDate());
//                gstResponse.setCreatedAt(gst.getCreatedAt());
//                gstResponse.setUpdatedAt(gst.getUpdatedAt());
//                gstResponse.setEnable(gst.isEnable());
//
//                // Add the GstResponse object to the list
//                gstResponseList.add(gstResponse);
//            }
//
//            // Return the list of GstResponse objects
//            return gstResponseList;
//        } else {
//            // Company data does not exist, return a specific message
//            throw new CompanyNotFoundException("Company is not present or enter a valid company ID");
//        }
//    }
//
//    @Override
//    public GstResponse getGstData(Long gstId, Long companyId) {
//
//        Optional<Company> companyData = companyRepository.findById(companyId);
//        GstResponse response = new GstResponse();
//
//        if (companyData.isPresent()) {
//            Optional<Gst> gstData = gstRepository.findByIdAndCompany_Id(gstId, companyId);
//
//            if (gstData.isPresent()) {
//                // Create a GstResponse object or process the Gst entity as needed
//                Gst gstEntity = gstData.get();
//                response.setId(gstEntity.getId());
//                response.setGstNumber(gstEntity.getGstNumber());
//                response.setStateJurisdiction(gstEntity.getStateJurisdiction());
//                response.setRegistrationDate(gstEntity.getRegistrationDate());
//                response.setCreatedAt(gstEntity.getCreatedAt());
//                response.setUpdatedAt(gstEntity.getUpdatedAt());
//                response.setEnable(gstEntity.isEnable());
//                response.setCompanyId(companyId); // Assuming companyId is part of GstResponse
//                response.setCompanyName(companyData.get().getCompanyName()); // Assuming company name is part of Company entity
//
//
////                // Now you can set the businessUnitResponseList. You would need to convert BusinessUnit entities to BusinessUnitResponse objects.
////                List<BusinessUnitResponse> businessUnitResponses = new ArrayList<>();
////                for (BusinessUnit businessUnit : gstEntity.getBusinessUnits()) {
////                    BusinessUnitResponse businessUnitResponse = new BusinessUnitResponse();
////                    // Set values in the businessUnitResponse object here
////                    // Example: businessUnitResponse.setId(businessUnit.getId());
////                    businessUnitResponses.add(businessUnitResponse);
////                }
////                response.setBusinessUnitResponseList(businessUnitResponses);
//
//            }
//
//        }
//        return response;
//    }
//
//    @Override
//    public GstResponse removeGstdata(Long gstId) {
//
////        Optional<Gst> gstOptional = gstRepository.findById(gstId);
////
////        if (gstOptional.isPresent()) {
////
////            Gst gst = gstOptional.get();
////            // Delete associated business units
////            List<BusinessUnit> businessUnits = gst.getBusinessUnits();
////            businessUnitRepository.deleteAll(businessUnits);
////
////
////            // Delete the GST record
////            gstRepository.delete(gst);
////
////
////            // Create and return a GstResponse object based on the deleted GST data
////            GstResponse removedGstResponse = new GstResponse();
////            removedGstResponse.setId(gst.getId());
////            removedGstResponse.setGstNumber(gst.getGstNumber());
////            removedGstResponse.setStateJurisdiction(gst.getStateJurisdiction());
////            // Set other fields as needed
////            return removedGstResponse;
////        }
//
//        return null;
//    }
//}
