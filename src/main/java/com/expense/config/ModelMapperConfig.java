package com.expense.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.expense.model.User;
import com.expense.model.DTO.UserDTO;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        
        // Set the matching strategy to STRICT
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        // Define the property map to avoid redundant mappings
        modelMapper.addMappings(new PropertyMap<UserDTO, User>() {
            @Override
            protected void configure() {
                // Explicitly map userName to name
//                map(source.getUserName(), destination.getUserName());
//                map(source.getUsertype(), destination.getUsertype());
            }
        });

        return modelMapper;
    }
}
	