package com.ecoral.fiap.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI Documentacao() {
        Server apiServer = new Server();
        apiServer.setUrl("http://localhost:8080");
        apiServer.description("Desenvolvimento do projeto Ecoral - GS");

        Contact dados = new Contact();
        dados.setName("Leonardo");
        dados.setName("Regina");
        dados.setName("Jhonn");

        Info info = new Info().title("Ecoral")
                .version("1")
                .description("Desenvolvimento da API Ecoral");
        return new OpenAPI().info(info).servers(List.of(apiServer));
    }

}
