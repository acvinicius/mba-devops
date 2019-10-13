package com.fiap.netflix.licensing.clients;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fiap.netflix.licensing.model.Organization;

@FeignClient("netflixservice")
public interface OrganizationFeignClient {
	@RequestMapping(method = RequestMethod.GET, value = "/v1/netflix/{organizationId}", consumes = "application/json")
	Organization getOrganization(@PathVariable("organizationId") String organizationId);
}
