package com.lawzoom.companyservice.repository;


import com.lawzoom.companyservice.model.businessUnitModel.BusinessUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusinessUnitRepository extends JpaRepository<BusinessUnit,Long>

{
    BusinessUnit findByAddress(String address);

    List<BusinessUnit> findByGstId(Long gstId);
}
