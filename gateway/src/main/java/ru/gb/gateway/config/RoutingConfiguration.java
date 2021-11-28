package ru.gb.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.gb.gateway.filters.SimpleFilter;

@Configuration
public class RoutingConfiguration {

    @Bean
    public SimpleFilter simpleFilter() {
        return new SimpleFilter();
    }
}
