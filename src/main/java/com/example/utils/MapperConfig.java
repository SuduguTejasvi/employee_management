package com.example.utils;


import org.junit.jupiter.api.BeforeEach;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

@Configuration
public class MapperConfig {
    @Value("${username}")
    private String username;
    @Bean
    public ModelMapper modelMapper(){
        System.out.println(username);
        return new ModelMapper();
    }
}
