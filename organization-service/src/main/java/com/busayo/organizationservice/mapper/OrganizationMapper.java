package com.busayo.organizationservice.mapper;

import com.busayo.organizationservice.dto.OrganizationDto;
import com.busayo.organizationservice.entity.Organization;

public class OrganizationMapper {

    public static OrganizationDto mapToOrganizationDto(Organization organization){
        OrganizationDto organizationDto = new OrganizationDto(
                organization.getId(),
                organization.getName(),
                organization.getDescription(),
                organization.getCode(),
                organization.getCreatedDate()
        );
        return organizationDto;
    }

    public static Organization mapToOrganization(OrganizationDto organizationDto){
        Organization organization = new Organization(
                organizationDto.getId(),
                organizationDto.getName(),
                organizationDto.getDescription(),
                organizationDto.getCode(),
                organizationDto.getCreatedDate()
        );
        return organization;
    }
}
