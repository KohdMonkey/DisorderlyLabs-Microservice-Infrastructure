package com.disorderlylabs.cart;


import org.springframework.context.annotation.Bean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//tracing
import brave.Tracing;
import brave.sampler.Sampler;
import brave.propagation.B3Propagation;
import brave.propagation.ExtraFieldPropagation;

import org.springframework.web.client.RestTemplate;
import zipkin2.codec.Encoding;
import zipkin2.reporter.AsyncReporter;
import zipkin2.reporter.okhttp3.OkHttpSender;



@SpringBootApplication
public class CartApplication {

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Tracing zipkinTracer() {
        OkHttpSender sender = OkHttpSender.newBuilder()
                .encoding(Encoding.JSON)
                .endpoint("http://10.0.0.24:9411/api/v2/spans")
                .build();
        AsyncReporter<zipkin2.Span> spanReporter = AsyncReporter.builder(sender).build();

        Tracing tracing = Tracing.newBuilder()
                .localServiceName("application")
                .spanReporter(spanReporter)
                .traceId128Bit(true)
                .sampler(Sampler.ALWAYS_SAMPLE)
                .propagationFactory(ExtraFieldPropagation.newFactory(B3Propagation.FACTORY,"InjectFault"))
                .build();

        return tracing;
    }


	public static void main(String[] args) {
		SpringApplication.run(CartApplication.class, args);
	}
}
