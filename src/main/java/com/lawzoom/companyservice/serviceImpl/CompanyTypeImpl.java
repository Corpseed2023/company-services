package com.lawzoom.companyservice.serviceImpl;

import com.lawzoom.companyservice.model.companyModel.CompanyType;
import com.lawzoom.companyservice.repository.companyRepo.CompanyTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CompanyTypeImpl {

    @Autowired
    private CompanyTypeRepository companyTypeRepository;

    public CompanyType createCompanyType(CompanyType companyType) {

       companyType.setBasicCompany(companyType.getBasicCompany());
       companyType.setId(companyType.getId());
       companyType.setCompanyTypeName(companyType.getCompanyTypeName());

        companyType.setCreatedAt(new Date());
        companyType.setUpdatedAt(new Date());
        companyType.setEnable(true);

        return companyTypeRepository.save(companyType);
    }
}
