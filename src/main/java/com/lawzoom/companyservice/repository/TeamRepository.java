package com.lawzoom.companyservice.repository;

import com.lawzoom.companyservice.model.teamModel.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team,Long> {
    List<Team> findAllByCompanyId(Long companyId);
}
