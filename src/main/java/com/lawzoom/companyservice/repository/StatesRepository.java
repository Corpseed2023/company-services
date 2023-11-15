package com.lawzoom.companyservice.repository;


import com.lawzoom.companyservice.model.region.States;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatesRepository extends JpaRepository<States,Long>

{



}
