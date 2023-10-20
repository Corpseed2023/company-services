package com.lawzoom.companyservice.controller.gstController;


import com.lawzoom.companyservice.dto.gstDto.GstRequest;
import com.lawzoom.companyservice.dto.gstDto.GstResponse;
import com.lawzoom.companyservice.model.gstModel.Gst;
import com.lawzoom.companyservice.service.GstService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/company/gst/{companyId}")
public class GstController {


    @Autowired
    private GstService gstService;


    @PostMapping("/save")
    public ResponseEntity<GstResponse> createGst(@RequestBody GstRequest gstRequest) {
        GstResponse createdGst = gstService.createGst(gstRequest);
        return new ResponseEntity<>(createdGst, HttpStatus.CREATED);
    }


    @PutMapping("/update/{gstId}")
    public ResponseEntity<GstResponse> updateGst(
            @RequestParam("companyId") Long companyId,
            @RequestParam("gstId") Long gstId,
            @RequestBody GstRequest gstRequest) {
        GstResponse updatedGst = gstService.updateGst(companyId, gstId, gstRequest);
        return new ResponseEntity<>(updatedGst, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<GstResponse>> getAllGst(@RequestParam("companyId") Long companyId) {
        List<GstResponse> allGst = gstService.getAllGst(companyId);
        return new ResponseEntity<>(allGst, HttpStatus.OK);
    }


    @GetMapping("/getGst")
    public ResponseEntity<GstResponse> getGstData(
            @RequestParam Long gstId,
            @RequestParam Long companyId
    ) {
        try {
            GstResponse gstData = gstService.getGstData(gstId, companyId);
            return ResponseEntity.ok(gstData);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/removeGst")
    public ResponseEntity<GstResponse> deleteGst(@RequestParam Long gstId) {
        GstResponse removedGstData = gstService.removeGstdata(gstId);
        if (removedGstData != null) {
            return new ResponseEntity<>(removedGstData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
