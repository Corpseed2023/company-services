package com.lawzoom.companyservice.repository.regionRepo;

import com.lawzoom.companyservice.model.region.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City,Long> {

    List<City> findByIsEnable(boolean isEnable);

}
