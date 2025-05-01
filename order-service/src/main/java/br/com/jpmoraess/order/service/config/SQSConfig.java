package br.com.jpmoraess.order.service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

import java.net.URI;

@Configuration
public class SQSConfig {

    private final String url;

    public SQSConfig(@Value("${app.sqs.url:http://localhost:4566}") String url) {
        this.url = url;
    }

    @Bean
    public SqsAsyncClient sqsAsyncClient() {
        return SqsAsyncClient.builder()
                .endpointOverride(URI.create(url))
                .region(Region.US_EAST_1)
                .build();
    }
}
