package com.intern.study.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @packageName    : ${package_name}
 * @fileName       : ${file_name}
 * @author         : ${user}
 * @date           : ${id:date('YYYY.MM.dd')}
 * @description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * ${id:date('YYYY.MM.dd')}         ${user}     최초 생성
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
                .description("인턴 교육 REST API")
                .version("1.0.0");
    }
}
