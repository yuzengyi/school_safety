package com.example.springboot.config;

//yzy
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration // 配置类
@EnableSwagger2 // 开启 swagger2 的自动配置
@Service
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(this.apiInfo())
                .groupName("智慧校园后台接口")
                //false 则不能在浏览器访问，true为默认。
                .enable(true)
                .select()
                //配置扫描接口的方式，基于包去扫描
                .apis(RequestHandlerSelectors.basePackage("com.example.springboot.controller"))
                //paths()过滤什么路径
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    private List<ApiKey> securitySchemes() {
        List<ApiKey> apiKeyList = new ArrayList<>();
        apiKeyList.add(new ApiKey("token", "Authorization", "header"));
        return apiKeyList;
    }

    private List<SecurityContext> securityContexts() {
        List<SecurityContext> securityContexts = new ArrayList<>();
        securityContexts.add(
                SecurityContext.builder()
                        .securityReferences(defaultAuth())
                        .forPaths(PathSelectors.regex("^(?!auth).*$"))
                        .build());
        return securityContexts;
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        List<SecurityReference> securityReferences = new ArrayList<>();
        securityReferences.add(new SecurityReference("token", authorizationScopes));
        return securityReferences;
    }

    /**
     * 创建该API的基本信息（这些基本信息会展现在文档页面中）
     * 访问地址：http://项目实际地址/swagger-ui.html
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("springboot利用swagger构建api文档")
                .description("基于小组管理的管理员系统")
                .termsOfServiceUrl("https://XXXX/XXXXX/group-manager/tree/develop/")
                .version("1.0")
                .build();
    }



}

