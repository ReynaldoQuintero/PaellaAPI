/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.swagger.configuration;

import com.fasterxml.classmate.TypeResolver;
import static com.google.common.collect.Sets.newHashSet;
import io.swagger.annotations.Api;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import springfox.documentation.builders.OperationBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiDescription;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ApiListingScannerPlugin;
import springfox.documentation.spi.service.contexts.DocumentationContext;
import springfox.documentation.spring.web.readers.operation.CachingOperationNameGenerator;

/**
 *
 * @author reyna
 */
public class SwaggerManualApiPlugin implements ApiListingScannerPlugin {
    
    @Autowired
    private CachingOperationNameGenerator operationNames;

    @Override
    public List<ApiDescription> apply(DocumentationContext dc) {
        Set<String> tags = new HashSet<String>();
        tags.add("Oauth2");
        return new ArrayList<ApiDescription>(
        Arrays.asList( 
            new ApiDescription(
                null,
                "/oauth/token",
                "Get an auth token based on username and password",
                Arrays.asList( 
                    new OperationBuilder(
                        operationNames)
                        .tags(tags)
                        .summary("oauth2")
                        .codegenMethodNameStem("oauthTokenPOST") 
                        .method(HttpMethod.POST)
                        .notes("Get an auth token based on username and password")
                        .parameters(
                            Arrays.asList( 
                                new ParameterBuilder()
                                    .description("The grant type used. Only has 'password' supported")
                                    .type(new TypeResolver().resolve(String.class))
                                    .name("grant_type")
                                    .parameterType("query")
                                    .required(true)
                                    .modelRef(new ModelRef("string")) 
                                    .build(),
                                new ParameterBuilder()
                                    .description("The username to obtain the token")
                                    .type(new TypeResolver().resolve(String.class))
                                    .name("username")
                                    .parameterType("query")
                                    .required(true)
                                    .modelRef(new ModelRef("string")) 
                                    .build(),
                                new ParameterBuilder()
                                    .description("The password to obtain the token")
                                    .type(new TypeResolver().resolve(String.class))
                                    .name("password")
                                    .parameterType("query")
                                    .required(true)
                                    .modelRef(new ModelRef("string")) 
                                    .build()
                                ))
                        .responseMessages(responseMessages()) 
                        .responseModel(new ModelRef("string")) 
                        .build()),
                false)));
    }
    
    private Set<ResponseMessage> responseMessages() { 
    return newHashSet(new ResponseMessageBuilder()
        .code(200)
        .message("Ok")
        .responseModel(new ModelRef("TokenResponse"))
        .build());
  }

    @Override
    public boolean supports(DocumentationType s) {
        return DocumentationType.SWAGGER_2.equals(s);
    }
    
}
