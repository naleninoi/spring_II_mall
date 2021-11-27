package ru.gb.java1154.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.gb.java1154.filters.SimpleFilter;

@Configuration
public class RoutingConfiguration {

    @Bean
    public SimpleFilter simpleFilter() {
        return new SimpleFilter();
    }
}
