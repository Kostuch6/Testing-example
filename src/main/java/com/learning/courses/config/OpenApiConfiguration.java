package com.learning.courses.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

  @Bean
  public OpenAPI buildOpenAPI() {
    final Info info = new Info()
        .title("Courses API")
        .version("1.0.0 RC1");

    return new OpenAPI()
        .info(info);
  }
}
