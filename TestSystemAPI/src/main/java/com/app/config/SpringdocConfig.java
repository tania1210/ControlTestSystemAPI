package com.app.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition
@ComponentScan("com.example.controller")
public class SpringdocConfig {

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.components(new Components()
						.addSecuritySchemes("ApiKey",
								new SecurityScheme()
										.type(SecurityScheme.Type.HTTP)
										.scheme("bearer")
										.bearerFormat("JWT")
						)
				)
				.info(new io.swagger.v3.oas.models.info.Info()
						.title("API")
						.version("v1")
						.description("Опис API для вашого проекту")
						.contact(new io.swagger.v3.oas.models.info.Contact()
								.name("Ваше Ім'я")
								.email("ваш_емейл@domain.com")
						)
						.license(new io.swagger.v3.oas.models.info.License()
								.name("Ліцензія API")
								.url("https://example.com/license")
						)
				)
				.addSecurityItem(new io.swagger.v3.oas.models.security.SecurityRequirement().addList("bearerAuth"));
	}
}
