package io.swagger.configuration;

import io.swagger.model.TokenResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ApiListingScannerPlugin;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import com.fasterxml.classmate.TypeResolver;
import io.swagger.annotations.Tag;
import org.springframework.beans.factory.annotation.Autowired;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-04-07T10:08:47.568Z[GMT]")
@Configuration
@EnableSwagger2
public class SwaggerDocumentationConfig {

    @Autowired
    TypeResolver typeResolver;
    
    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("Paella API")
            .description("API of the restaurant 'Paella', managing authentication, food menu display, order creation, and payment receival. This was build as an exercise for the subject Web Services and Enterprise Architecture in the UT1 master Innovative Information Systems.")
            .license("")
            .licenseUrl("http://unlicense.org")
            .termsOfServiceUrl("")
            .version("1.0.0")
            .contact(new Contact("","", ""))
            .build();
    }

    @Bean
    public Docket customImplementation(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                    .apis(RequestHandlerSelectors.basePackage("io.swagger.api"))
                    .build()
                .directModelSubstitute(org.threeten.bp.LocalDate.class, java.sql.Date.class)
                .directModelSubstitute(org.threeten.bp.OffsetDateTime.class, java.util.Date.class)
                .additionalModels(typeResolver.resolve(TokenResponse.class))
                .apiInfo(apiInfo());
    }
    
    @Bean
    public ApiListingScannerPlugin listingScanner() {
        return new SwaggerManualApiPlugin();
    }

}
