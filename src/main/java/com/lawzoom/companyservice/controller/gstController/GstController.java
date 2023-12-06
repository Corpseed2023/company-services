package com.lawzoom.companyservice.controller.gstController;


import com.lawzoom.companyservice.dto.gstDto.GstRequest;
import com.lawzoom.companyservice.dto.gstDto.GstResponse;
import com.lawzoom.companyservice.services.GstService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
//@RequestMapping("/company/gst/{companyId}")
@RequestMapping("/company/gst")

public class GstController {


    @Autowired
    private GstService gstService;


    @PostMapping("/createGst")
    public ResponseEntity<GstResponse> createGst(@RequestBody GstRequest gstRequest ,@RequestParam Long businessUnitId) {
        GstResponse createdGst = gstService.createGst(gstRequest,businessUnitId);
        return new ResponseEntity<>(createdGst, HttpStatus.CREATED);
    }


    @PutMapping("/editGst")
    public ResponseEntity<GstResponse> updateGst(
            @RequestParam("companyId") Long businessUnitId,
            @RequestParam("gstId") Long gstId,
            @RequestBody GstRequest gstRequest) {
        GstResponse updatedGst = gstService.updateGst(businessUnitId, gstId, gstRequest);
        return new ResponseEntity<>(updatedGst, HttpStatus.OK);
    }

    @GetMapping("/getAllGst")
    public ResponseEntity<List<GstResponse>> getAllGst(@RequestParam("businessUnitId") Long businessUnitId) {
        List<GstResponse> allGst = gstService.getAllGst(businessUnitId);
        return new ResponseEntity<>(allGst, HttpStatus.OK);
    }


//    @GetMapping("/getGst")
//    public ResponseEntity<GstResponse> getGstData(
//            @RequestParam Long gstId,
//            @RequestParam Long companyId
//    ) {
//        try {
//            GstResponse gstData = gstService.getGstData(gstId, companyId);
//            return ResponseEntity.ok(gstData);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }

//    @DeleteMapping("/removeGst")
//    public ResponseEntity<GstResponse> deleteGst(@RequestParam Long gstId) {
//        GstResponse removedGstData = gstService.removeGstdata(gstId);
//        if (removedGstData != null) {
//            return new ResponseEntity<>(removedGstData, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
}
