package com.lawzoom.companyservice.repository;


import com.lawzoom.companyservice.model.businessUnitModel.BusinessUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessUnitRepository extends JpaRepository<BusinessUnit,Long>

{

    BusinessUnit findByAddress(String address);
}
