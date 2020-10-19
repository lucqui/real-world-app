package com.lsq.realWorldApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.concurrent.Executor;

@SpringBootApplication
@EnableAsync
@EnableWebMvc
public class RealWorldApp {

	public static void main(String[] args) {
		SpringApplication.run(RealWorldApp.class, args);
	}

	@Bean
	public Executor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(2);
		executor.setMaxPoolSize(4);
		executor.setQueueCapacity(500);
		executor.setThreadNamePrefix("real-world-executor");
		executor.initialize();
		return executor;
	}

}