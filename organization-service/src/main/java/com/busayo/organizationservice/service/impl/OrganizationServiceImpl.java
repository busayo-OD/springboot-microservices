package com.busayo.organizationservice.service.impl;

import com.busayo.organizationservice.dto.OrganizationDto;
import com.busayo.organizationservice.entity.Organization;
import com.busayo.organizationservice.mapper.OrganizationMapper;
import com.busayo.organizationservice.repository.OrganizationRepository;
import com.busayo.organizationservice.service.OrganizationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private OrganizationRepository organizationRepository;

    @Override
    public OrganizationDto saveOrganization(OrganizationDto organizationDto) {

//        convert OrganizationDto int Organization jpa entity
        Organization organization = OrganizationMapper.mapToOrganization(organizationDto);

        Organization savedOrganization = organizationRepository.save(organization);
        return OrganizationMapper.mapToOrganizationDto(savedOrganization);
    }

    @Override
    public OrganizationDto getOrganizationByCode(String code) {
        Organization organization = organizationRepository.findByCode(code);
        return OrganizationMapper.mapToOrganizationDto(organization);
    }
}
