package com.service.BVHSHOP.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tools.jackson.datatype.hibernate7.Hibernate7Module;

@Configuration
public class JacksonConfig {
    @Bean
    public Hibernate7Module hibernate7Module() {
        return new Hibernate7Module();
    }
}