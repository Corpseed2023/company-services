package com.lawzoom.companyservice.repository;

import com.lawzoom.companyservice.model.teamModel.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team,Long> {
}
