package com.acruent.admin.swaggerconfiguration;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdminSwaggerConfiguration {

	@Bean
	public GroupedOpenApi controllerApi()
	{
	        return GroupedOpenApi.builder()
	                .group("AdminModule")
	                .packagesToScan("com.acruent.admin.controller") // Specify the package to scan
	                .build();
	 }
}
