package com.app.config;

import java.util.List;

import org.springdoc.core.models.GroupedOpenApi;
import org.springdoc.core.utils.SpringDocUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.app.service.TestService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
//import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@OpenAPIDefinition
@Configuration
@ComponentScan("com.example.controller")
public class SpringdocConfig {

	protected static final String AUTH_NAME = "ApiKey";
    protected static final String TITLE = "Uni-file-archive";
    protected static final String DESCRIPTION = "";
    protected static final String VERSION = "1.0";

	private Info getInfo() {
	    return new Info()
	            .title(TITLE)
	            .version(VERSION)
	            .description(DESCRIPTION);
	}

	@Bean
	public OpenAPI customOpenAPI() {
	    return new OpenAPI()
	            .components(new Components().addSecuritySchemes(AUTH_NAME, new SecurityScheme()
	                    .name(AUTH_NAME)
	                    .type(SecurityScheme.Type.APIKEY)
	                    .in(SecurityScheme.In.HEADER)))
	            .addSecurityItem(new SecurityRequirement().addList(AUTH_NAME)
	            ).info(getInfo());
	}
	    
}
