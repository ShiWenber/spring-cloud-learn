package edu.ynu.ecs.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//knife4j使用openapi3风格
@Configuration
public class SysSpringdocConfig {

//    @Bean
//    public GroupedOpenApi testApiDoc() {
//        return GroupedOpenApi.builder()
//                .group("test-controller")
//                .packagesToScan("edu.ynu.ecs.controllers")
//                .build();
//    }

    @Bean
    public GroupedOpenApi sysPlatformApiDoc() {
        return GroupedOpenApi.builder()
                .group("sys-platform")
                .packagesToScan("edu.ynu.ecs.controllers")
                .build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("电子商务系统API")
                        .version("1.0.0")
                        .description("Knife4j集成springdoc-openapi示例")
                        .termsOfService("http://doc.xiaominfo.com")
                        .license(new License().name("Apache 2.0")
                                .url("http://doc.xiaominfo.com"))
                )
//			添加swagger的安全认证主键
			.components(new Components()
				.addSecuritySchemes("Authorization",
					new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")));
    }


}
