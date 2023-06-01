package com.busayo.organizationservice.repository;

import com.busayo.organizationservice.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    Organization findByCode(String code);
}
