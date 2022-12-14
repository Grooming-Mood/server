package com.be.grooming_mood.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableWebMvc
public class SwaggerConfig {

    @Bean
    public Docket swaggerAPI(){
        //Docket : swagger Bean
        return new Docket(DocumentationType.OAS_30)
                .useDefaultResponseMessages(true) //기본 응답 메시지 표시 여부
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.be.grooming_mood")) //swagger 탐색 대상 패키지
                .paths(PathSelectors.any())
                .build();

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Grooming Mood Swagger")
                .description("Grooming Mood swagger")
                .version("1.0")
                .build();
    }

}