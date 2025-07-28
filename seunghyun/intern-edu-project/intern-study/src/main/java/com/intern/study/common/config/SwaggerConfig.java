package com.intern.study.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @packageName    : com.intern.study.common.config
 * @fileName       : SwaggerConfig.java
 * @author         : intern
 * @date           : 2024.12.19
 * @description    : Swagger(OpenAPI) 설정 클래스
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024.12.19         intern     최초 생성
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI().info(this.apiInfo());
    }

    private Info apiInfo(){
        return new Info()
                .title("Intern Study Swagger")
                .description("인턴 교육 Rest API")
                .version("1.0.0");
    }

}
