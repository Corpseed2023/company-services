package com.lawzoom.companyservice.repository;


import com.lawzoom.companyservice.model.gstModel.Gst;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GstRepository extends JpaRepository<Gst, Long> {

    List<Gst> findByCompanyId(Long companyId);

    Optional<Gst> findByIdAndCompany_Id(Long gstId, Long companyId);
}
