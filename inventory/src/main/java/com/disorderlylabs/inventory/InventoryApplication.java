package com.disorderlylabs.inventory;

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
public class InventoryApplication {
    @Bean
    RestTemplate template() {
        return new RestTemplate();
    }

	public static void main(String[] args) {
		SpringApplication.run(InventoryApplication.class, args);
	}
}
