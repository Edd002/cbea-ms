package com.pucminas.cbea.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Value("${server.swagger.base.url}")
    private String baseUrl;

    @Bean
    public OpenAPI customOpenAPI() {
        Server server = new Server();
        server.setUrl(baseUrl);

        return new OpenAPI()
                .info(new Info()
                        .title("CBEA API")
                        .version("v1.0")
                        .description("Documentation API")
                        .contact(new Contact().name("CBEA Development Team").email("contato@cbea.com.br")))
                .servers(List.of(server));
    }
}