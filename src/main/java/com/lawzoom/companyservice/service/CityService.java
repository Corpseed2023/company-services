package com.lawzoom.companyservice.service;

import com.lawzoom.companyservice.model.region.City;
import com.lawzoom.companyservice.model.region.States;

import java.util.List;

public interface CityService {


    List<City> getAllCities();

    List<City> createOrUpdateCities(List<String> citiesName,Long stateId);

    void deleteCity(Long id);
}
