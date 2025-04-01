package midianet.mykudos.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final String DEFAULT_MESSAGE_200_SUCCESS = "Sucesso, Operação realizada.";
    private static final String DEFAULT_MESSAGE_201_CREATED = "Sucesso, Recurso Criado.";
    private static final String DEFAULT_MESSAGE_204_NOCONTENT = "Sucesso, Recurso Removido.";
    private static final String DEFAULT_MESSAGE_401_NOTAUTHORIZED = "Erro, Você não está autorizado a ver o recurso.";
    private static final String DEFAULT_MESSAGE_403_FORBIDDEN = "Erro, O acesso ao recurso que você estava tentando acessar é proibido.";
    private static final String DEFAULT_MESSAGE_404_NOT_FOUND = "Erro, O recurso que você estava tentando acessar não foi encontrado.";
    private static final String DEFAULT_MESSAGE_500_SERVER_ERROR = "Erro, Ocorreu algo inesperado no servidor, contate o suporte.";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("midianet.mykudos.infra.http.rest"))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .globalResponses(HttpMethod.GET,
                        List.of(new ResponseBuilder().code("200").description(DEFAULT_MESSAGE_200_SUCCESS).build(),
                                new ResponseBuilder().code("401").description(DEFAULT_MESSAGE_401_NOTAUTHORIZED).build(),
                                new ResponseBuilder().code("403").description(DEFAULT_MESSAGE_403_FORBIDDEN).build(),
                                new ResponseBuilder().code("404").description(DEFAULT_MESSAGE_404_NOT_FOUND).build(),
                                new ResponseBuilder().code("500").description(DEFAULT_MESSAGE_500_SERVER_ERROR).build()
                        ))
                .globalResponses(HttpMethod.POST,
                        List.of(new ResponseBuilder().code("201").description(DEFAULT_MESSAGE_201_CREATED).build(),
                                new ResponseBuilder().code("401").description(DEFAULT_MESSAGE_401_NOTAUTHORIZED).build(),
                                new ResponseBuilder().code("403").description(DEFAULT_MESSAGE_403_FORBIDDEN).build(),
                                new ResponseBuilder().code("404").description(DEFAULT_MESSAGE_404_NOT_FOUND).build(),
                                new ResponseBuilder().code("500").description(DEFAULT_MESSAGE_500_SERVER_ERROR).build()
                        )
                )
                .globalResponses(HttpMethod.PUT,
                        List.of(new ResponseBuilder().code("200").description(DEFAULT_MESSAGE_200_SUCCESS).build(),
                                new ResponseBuilder().code("401").description(DEFAULT_MESSAGE_401_NOTAUTHORIZED).build(),
                                new ResponseBuilder().code("403").description(DEFAULT_MESSAGE_403_FORBIDDEN).build(),
                                new ResponseBuilder().code("404").description(DEFAULT_MESSAGE_404_NOT_FOUND).build(),
                                new ResponseBuilder().code("500").description(DEFAULT_MESSAGE_500_SERVER_ERROR).build()
                        )
                )
                .globalResponses(HttpMethod.DELETE,
                        List.of(
                                new ResponseBuilder().code("204").description(DEFAULT_MESSAGE_204_NOCONTENT).build(),
                                new ResponseBuilder().code("401").description(DEFAULT_MESSAGE_401_NOTAUTHORIZED).build(),
                                new ResponseBuilder().code("403").description(DEFAULT_MESSAGE_403_FORBIDDEN).build(),
                                new ResponseBuilder().code("404").description(DEFAULT_MESSAGE_404_NOT_FOUND).build(),
                                new ResponseBuilder().code("500").description(DEFAULT_MESSAGE_500_SERVER_ERROR).build()
                        )
                )
//                .tags(new Tag("Cliente", "Dados relacionados ao cliente."))
//                .tags(new Tag("Produto", "Dados relacionados ao produto."))
                //.securitySchemes(List.of(apiKey()))
                //.securityContexts(List.of(securityContext()))
                .apiInfo(apiInfo());
    }

    //    private ApiKey apiKey() {
    //        return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
    //    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("mykudos-api").description("API").version("1.0.0").build();
    }
    //    private SecurityContext securityContext() {
    //        return SecurityContext.builder()
    //                .securityReferences(defaultAuth())
    //                .operationSelector(oc -> oc.requestMappingPattern().matches(DEFAULT_INCLUDE_PATTERN))
    //                .build();
    //    }

    //    private List<SecurityReference> defaultAuth() {
    //        final var authorizationScopes = new AuthorizationScope[1];
    //        authorizationScopes[0] = new AuthorizationScope("global", "accessEverything");
    //        return List.of(new SecurityReference("JWT", authorizationScopes));
    //    }

}
//import io.swagger.models.auth.In;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.builders.ResponseMessageBuilder;
//import springfox.documentation.service.*;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spi.service.contexts.SecurityContext;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//import java.util.Arrays;
//import java.util.List;
//
//@Configuration
//public class SwaggerConfig{
//
//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("meta.smart.api.resource"))
//                .paths(PathSelectors.any()).build()
//                .useDefaultResponseMessages(false)
//                .globalResponseMessage(RequestMethod.GET, globalGetResponseMessages())
//                .globalResponseMessage(RequestMethod.POST, globalPostPutResponseMessages())
//                .globalResponseMessage(RequestMethod.PUT, globalPostPutResponseMessages())
//                .globalResponseMessage(RequestMethod.DELETE, globalDeleteResponseMessages())
//                .apiInfo(apiInfo())
//                .securitySchemes(Arrays.asList(new ApiKey("Token Access", HttpHeaders.AUTHORIZATION, In.HEADER.name())))
//                .securityContexts(Arrays.asList(securityContext()))
//                .tags(new Tag("Address", "Dados relacionados ao endereço."))
//                .tags(new Tag("Company", "Dados relacionados a empresa."))
//                .tags(new Tag("Geo", "Dados relacionados ao georeferenciamento."))
//                .tags(new Tag("User", "Dados relacionados ao usuário."));
//
//    }
//
//    private List<ResponseMessage> globalGetResponseMessages(){
//        return Arrays.asList(
//                new ResponseMessageBuilder()
//                    .code("HttpStatus.INTERNAL_SERVER_ERROR.value())
//                    .message("Erro interno do servidor")
//                    .build(),
//                new ResponseMessageBuilder()
//                    .code("HttpStatus.NOT_ACCEPTABLE.value())
//                    .message("Recurso não possui representação que poderia ser aceita pelo consumidor.")
//                    .build()
//        );
//    }
//
//    private List<ResponseMessage> globalPostPutResponseMessages() {
//        return Arrays.asList(
//                new ResponseMessageBuilder()
//                        .code("HttpStatus.BAD_REQUEST.value())
//                        .message("Requisição inválida (erro do cliente)")
//                        .build(),
//                new ResponseMessageBuilder()
//                        .code("HttpStatus.INTERNAL_SERVER_ERROR.value())
//                        .message("Erro interno no servidor")
//                        .build(),
//                new ResponseMessageBuilder()
//                        .code("HttpStatus.NOT_ACCEPTABLE.value())
//                        .message("Recurso não possui representação que poderia ser aceita pelo consumidor")
//                        .build(),
//                new ResponseMessageBuilder()
//                        .code("HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
//                        .message("Requisição recusada porque o corpo está em um formato não suportado")
//                        .build()
//        );
//    }
//
//    private List<ResponseMessage> globalDeleteResponseMessages() {
//        return Arrays.asList(
//                new ResponseMessageBuilder()
//                        .code("HttpStatus.BAD_REQUEST.value())
//                        .message("Requisição inválida (erro do cliente)")
//                        .build(),
//                new ResponseMessageBuilder()
//                        .code("HttpStatus.INTERNAL_SERVER_ERROR.value())
//                        .message("Erro interno no servidor")
//                        .build()
//        );
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("SMART-API-JAVA - Sistema Modelo de Arquitetura e Tecnologia")
//                .message("SMART-API-JAVA - Apis e Regras de Negócio do projeto smart" +
//                        "Interoperabilidade baseado no OpenAPI(Swagger) versão 3.")
//                .termsOfServiceUrl("http://springfox.io")
//                .contact(new Contact("Arquitetura Meta"
//                        , "https://github.com/OAI/OpenAPI-Specification/blob/master/versions/2.0.md"
//                        , "arquitetura@meta.com.br"))
//                .license("Apache License Version 2.0")
//                .licenseUrl("https://github.com/springfox/springfox/blob/master/LICENSE")
//                //.version(smartApiConfig.projectVersion)
//                .build();
//    }
//
//    private SecurityContext securityContext() {
//        return SecurityContext.builder()
//                .securityReferences(defaultAuth())
//                .forPaths(PathSelectors.ant("/**"))
//                .build();
//    }
//
//    List<SecurityReference> defaultAuth() {
//        AuthorizationScope authorizationScope
//                = new AuthorizationScope("ADMIN", "accessEverything");
//        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
//        authorizationScopes[0] = authorizationScope;
//        return Arrays.asList(
//                new SecurityReference("Token Access", authorizationScopes));
//    }
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("swagger-ui.html")
//                .addResourceLocations("classpath:/META-INF/resources/");
//
//        registry.addResourceHandler("/webjars/**")
//                .addResourceLocations("classpath:/META-INF/resources/webjars/");
//    }
//
//}
