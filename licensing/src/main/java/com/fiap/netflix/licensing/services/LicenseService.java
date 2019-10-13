package com.fiap.netflix.licensing.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.netflix.licensing.clients.OrganizationDiscoveryClient;
import com.fiap.netflix.licensing.clients.OrganizationFeignClient;
import com.fiap.netflix.licensing.clients.OrganizationRestTemplateClient;
import com.fiap.netflix.licensing.config.ServiceConfig;
import com.fiap.netflix.licensing.model.License;
import com.fiap.netflix.licensing.model.Organization;
import com.fiap.netflix.licensing.repository.LicenseRepository;



@Service
public class LicenseService {

	@Autowired
	private LicenseRepository licenseRepository;

	@Autowired
	ServiceConfig config;

	@Autowired
	OrganizationFeignClient organizationFeignClient;

	@Autowired
	OrganizationRestTemplateClient organizationRestClient;

	@Autowired
	OrganizationDiscoveryClient organizationDiscoveryClient;

	private Organization retrieveOrgInfo(String organizationId, String clientType) {
		Organization organization = null;
		switch (clientType) {
		case "feign":
			System.out.println("I am using the feign client");
			organization = organizationFeignClient.getOrganization(organizationId);
			break;
		case "rest":
			System.out.println("I am using the rest client");
			organization = organizationRestClient.getOrganization(organizationId);
			break;
		case "discovery":
			System.out.println("I am using the discovery client");
			organization = organizationDiscoveryClient.getOrganization(organizationId);
			break;
		default:
			System.out.println("I am using defautl (rest client)");
			organization = organizationRestClient.getOrganization(organizationId);
		}

		return organization;
	}

	public License getLicense(String organizationId, String licenseId, String clientType) {
//		License license = licenseRepository.findByOrganizationIdAndLicenseId(organizationId, licenseId);
		License license = new License();
		Organization org = retrieveOrgInfo(organizationId, clientType);
		return license.withOrganizationName(org.getName()).withContactName(org.getContactName())
				.withContactEmail(org.getContactEmail()).withContactPhone(org.getContactPhone())
				.withComment(config.getExampleProperty());
	}

	public List<License> getLicensesByOrg(String organizationId) {
		return licenseRepository.findByOrganizationId(organizationId);
	}

	public void saveLicense(License license) {
		license.withId(UUID.randomUUID().toString());
		licenseRepository.save(license);

	}

	public void updateLicense(License license) {
		licenseRepository.save(license);
	}

	public void deleteLicense(License license) {
		licenseRepository.delete(license.getLicenseId());
	}

}
