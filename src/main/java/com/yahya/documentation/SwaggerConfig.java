package com.yahya.documentation;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {

    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .host("api.wellwaretech.com")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.yahya"))
                .build()
                .apiInfo(metaData());
    }

    private Predicate<String> regex(String s) {
        return new Predicate<String>() {
            @Override
            public boolean apply(String s) {
                return false;
            }
        };
    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("GP REST API")
                .description("\"GP REST API.\"")
                .version("1.0.0")
                .license("GP Version 1.0")
                .contact(new Contact("GP", "https://api.wellwaretech.com", "yibrahim.py@gmail.com"))
                .build();
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

    }
}
