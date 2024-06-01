package com.eteration.simplebanking.model.Mapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfigiration {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}

