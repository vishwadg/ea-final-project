package edu.miu.ratingservice.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebSecurityConfig {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
