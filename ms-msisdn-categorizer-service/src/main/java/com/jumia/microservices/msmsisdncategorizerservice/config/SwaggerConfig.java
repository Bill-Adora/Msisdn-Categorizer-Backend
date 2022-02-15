package com.jumia.microservices.msmsisdncategorizerservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private static final String MSISDN_CATEGORIZER_SERVICE_DESCRIPTION =
            "This service lists and categorizes country phone numbers.";

    /**
     * Setup custom configurations for swagger Docket.
     *
     * @return Docker instance builder that provides custom configuration of Swagger documentation.
     */
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.jumia.microservices"))
                .build()
                .apiInfo(apiInfo()).tags(
                        new Tag("categorizer-controller",
                                "This controller allows clients to get categorized phone numbers.")
                );
    }

    /**
     * Setup custom Micro-services details to swagger
     *
     * @return ApiInfo object containing custom information about the API
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Ms-Msisdn-Categorizer-Service")
                .description(MSISDN_CATEGORIZER_SERVICE_DESCRIPTION)
                .version("0.0.1")
                .contact(
                        new Contact("Bilstone Adora",
                                "https://www.linkedin.com/in/bilstone-adora-769791a3/",
                                "adorabilstone@gmail.com")
                )
                .build();
    }
}
