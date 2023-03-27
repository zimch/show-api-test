package api.shop.online.onlineshopapi.service;

import api.shop.online.onlineshopapi.model.Organization;
import api.shop.online.onlineshopapi.model.User;

import java.util.Set;

public interface OrganizationService {
    Organization getOrganizationById(Long id);
    Organization getOrganizationByUser(User user);

    void save(Organization organization);
}
