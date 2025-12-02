package com.nownow.mybiz.onboarding.proxy.utils;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.HeaderParameter;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.servers.Server;
import jakarta.servlet.ServletContext;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebMvc
@SecurityScheme(
        name = "apiKeyAuth",
        scheme = "bearer",
        type = SecuritySchemeType.APIKEY,
        in = SecuritySchemeIn.HEADER,
        paramName = "X-API-KEY"
)
public class SwaggerUtilityConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowedOrigins("*");
    }

    @Bean
    public OpenAPI tokenManagementOpenAPI(ServletContext servletContext) {
        Server server = new Server().url(servletContext.getContextPath());
        return new OpenAPI()
                .info(new Info()
                        .title("Token Management Service API")
                        .description("""
                                The Token Management Service handles the generation, 
                                validation, and expiration of OTPs and verification tokens 
                                for authentication and password reset workflows.
                                """)
                        .version("v1.0.0")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org"))
                )
                .externalDocs(new ExternalDocumentation()
                        .description("Token Management Service Developer Docs")
                        .url("https://docs.nownow.com/token-management"))
                .servers(Collections.singletonList(server));
    }

    @Bean
    public OpenApiCustomizer addGlobalHeaders() {
        return openApi -> {
            List<Parameter> headers = List.of(
                    new HeaderParameter()
                            .name("X-API-KEY")
                            .required(true)
                            .description("API Key used for authenticating service-to-service communication")
                            .schema(new StringSchema())
            );

            openApi.getPaths().values().forEach(pathItem ->
                    pathItem.readOperations().forEach(operation -> operation.setParameters(headers))
            );
        };
    }
}
