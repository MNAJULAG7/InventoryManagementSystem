package com.inventory.sharedfiles;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanCreation {
    @Bean
    public ModelMapper modelMapper()
    {
        return new ModelMapper();
    }
}
