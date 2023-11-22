package com.lawzoom.companyservice.repository;


import com.lawzoom.companyservice.model.gstModel.Gst;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GstRepository extends JpaRepository<Gst, Long> {
    List<Gst> findByBusinessUnitId(Long companyId);

}