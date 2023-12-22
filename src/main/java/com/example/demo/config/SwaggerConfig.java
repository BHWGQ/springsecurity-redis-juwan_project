package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

@EnableOpenApi
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket docket(){
        return  new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.demo.controller"))
                .build()
                .pathMapping("/admin")
                .groupName("权子来咯");
    }
    // 配置swagger基本信息
    private ApiInfo apiInfo(){
        Contact contact = new Contact("权大王", "hyc.com", "2363531831@qq.com");
        return new ApiInfo(
                "权子的swagger",
                "大抵是对象太叛逆了!",
                "1.0",
                "hyc.com",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }
}
