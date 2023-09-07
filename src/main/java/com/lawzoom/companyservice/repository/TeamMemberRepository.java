package com.lawzoom.companyservice.repository;

import com.lawzoom.companyservice.model.teamMemberModel.TeamMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamMemberRepository extends JpaRepository<TeamMember,Long> {
}
