package com.example.config;

//import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Contact;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;

@OpenAPIDefinition
@Configuration
public class SwaggerConfig {
 
    @Bean
    public OpenAPI api() {
        return new OpenAPI().info(info())
//        		.group("Group")
//                .packagesToScan("com.example.testsystemapi.controller")
//                .pathsToMatch("/**")
//                .build()
;
    }

//	@Bean
//	public Docket api() {
//	    return new Docket(DocumentationType.SWAGGER_2)
//	            .select()
//	            .apis(RequestHandlerSelectors.basePackage("com.example.testsystemapi.controller"))
//	            .paths(PathSelectors.any())
//	            .build()
//	            .apiInfo(metaInfo())
//	            .pathMapping("/"); // Додайте цей рядок
//	}
//
    private Info info() {
        return new Info().title("Spring Doc")
        		.version("1.0.0")
        		.description("description")
//        		.contact(new Contact("Dev-Team", "http://example", "example@gmail.com"))
//        		.build();
        		;
    }
}

