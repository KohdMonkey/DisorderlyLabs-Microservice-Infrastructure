package com.disorderlylabs.app;


import org.springframework.web.client.RestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//tracing
import brave.Tracing;
import brave.sampler.Sampler;
import brave.propagation.B3Propagation;
import brave.propagation.ExtraFieldPropagation;

import zipkin2.reporter.AsyncReporter;
import zipkin2.reporter.okhttp3.OkHttpSender;


@SpringBootApplication
public class AppApplication {

	@Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}
}
