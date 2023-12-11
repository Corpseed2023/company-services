package com.lawzoom.companyservice.serviceImpl.regionLogic;

import com.lawzoom.companyservice.model.region.City;
import com.lawzoom.companyservice.model.region.States;
import com.lawzoom.companyservice.repository.regionRepo.CityRepository;
import com.lawzoom.companyservice.repository.regionRepo.StatesRepository;
import com.lawzoom.companyservice.services.regionService.CityService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityServiceImpl  implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private StatesRepository statesRepository;

    @Override
    public List<City> getAllCities() {
        return cityRepository.findByIsEnable(true);
    }



    @Override
    public List<City> createOrUpdateCities(List<String> citiesName, Long stateId) {

        List<City> citiesList = new ArrayList<>();

        States state = statesRepository.findById(stateId).orElseThrow(() -> new EntityNotFoundException("State not found"));

        for (String cityName : citiesName) {
            City city = new City();
            city.setCityName(cityName);
            city.setStates(state);
            city.setEnable(true);
            citiesList.add(city);
        }

        return cityRepository.saveAll(citiesList);
    }

    @Override
    public void deleteCity(Long id) {

        cityRepository.deleteById(id);
    }
}
