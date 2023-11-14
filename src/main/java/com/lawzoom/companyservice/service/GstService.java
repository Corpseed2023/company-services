package com.lawzoom.companyservice.service;

import com.lawzoom.companyservice.dto.gstDto.GstRequest;
import com.lawzoom.companyservice.dto.gstDto.GstResponse;

import java.util.List;

public interface GstService {

    GstResponse createGst(GstRequest gstRequest, Long businessUnitId);


    GstResponse updateGst(Long companyId, Long gstId, GstRequest gstRequest);


    List<GstResponse> getAllGst(Long companyId);

    GstResponse getGstData(Long gstId, Long companyId);


    GstResponse removeGstdata(Long gstId);
}
