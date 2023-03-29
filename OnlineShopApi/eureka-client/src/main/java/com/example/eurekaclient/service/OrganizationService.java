package com.example.eurekaclient.service;



import com.example.eurekaclient.model.Organization;
import com.example.eurekaclient.model.User;

import java.util.Set;

public interface OrganizationService {
    Organization getOrganizationById(Long id);
    Organization getOrganizationByUser(User user);

    void save(Organization organization);
}
