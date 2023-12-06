package com.lawzoom.companyservice.services;

import com.lawzoom.companyservice.dto.gstDto.GstRequest;
import com.lawzoom.companyservice.dto.gstDto.GstResponse;

import java.util.List;

public interface GstService {

    GstResponse createGst(GstRequest gstRequest, Long businessUnitId);


    GstResponse updateGst(Long businessUnitId, Long gstId, GstRequest gstRequest);


    List<GstResponse> getAllGst(Long businessUnitId);


}
