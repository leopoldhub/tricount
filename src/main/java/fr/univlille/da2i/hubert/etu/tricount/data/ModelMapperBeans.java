package fr.univlille.da2i.hubert.etu.tricount.data;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperBeans {

    @Bean
    public ModelMapper buildNewModelMapper() {
        return new ModelMapper();
    }

}
