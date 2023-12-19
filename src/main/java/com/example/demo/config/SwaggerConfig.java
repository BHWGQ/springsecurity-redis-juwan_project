package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
        return  new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo());
    }
    // 配置swagger基本信息
    private ApiInfo apiInfo(){
        Contact contact = new Contact("权大王", "hyc.com", "2363531831@qq.com");
        return new ApiInfo(
                "权子的swagger",
                "签名",
                "1.0",
                "hyc.com",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }

}
