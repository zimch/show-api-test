package api.shop.online.onlineshopapi.service;

import api.shop.online.onlineshopapi.model.Organization;
import api.shop.online.onlineshopapi.model.User;
import api.shop.online.onlineshopapi.repository.OrganizationRepository;
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
