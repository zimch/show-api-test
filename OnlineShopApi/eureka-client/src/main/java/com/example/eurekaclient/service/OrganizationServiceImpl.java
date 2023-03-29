package com.example.eurekaclient.service;


import com.example.eurekaclient.model.Organization;
import com.example.eurekaclient.model.User;
import com.example.eurekaclient.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class OrganizationServiceImpl implements OrganizationService{

    @Autowired
    OrganizationRepository organizationRepository;

    @Override
    public Organization getOrganizationById(Long id) {
        return organizationRepository.findById(id).orElseThrow();
    }

    @Override
    public Organization getOrganizationByUser(User user) {
        return user.getOrganization();
    }

    @Override
    public void save(Organization organization) {
        if (organization != null && !organizationRepository.existsById(organization.getId())) {
             organizationRepository.save(organization);
        }
    }
}
