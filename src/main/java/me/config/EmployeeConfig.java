package me.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.Executors;

@Configuration
public class EmployeeConfig {
    private final Integer connectionPoolSize;

    public EmployeeConfig(@Value("${spring.datasource.maximum-pool-size}") Integer connectionPoolSize) {
        this.connectionPoolSize = connectionPoolSize;
    }


    @Bean
    public Scheduler jdbcScheduler() {
        return Schedulers.fromExecutor(Executors.newFixedThreadPool(connectionPoolSize));
    }
}
