package com.edig.productsapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@OpenAPIDefinition(
        info = @io.swagger.v3.oas.annotations.info.Info(
                title = "Products Search Engine API",
                version = "1.0",
                description = "Products Search Engine API Documentation"
        )

)
public class ProductsApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductsApiApplication.class, args);
    }

}
