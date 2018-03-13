package com.example.demomicrometer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@EnableDiscoveryClient
public class DemoMicrometerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoMicrometerApplication.class, args);
    }

    @Bean
    @Profile("cloud")
    public MeterRegistryCustomizer meterRegistryCustomizer(
            @Value("${vcap.application.name:}") String applicationName,
            @Value("${vcap.application.instance_id:}") String instanceId
    ) {
        return registry -> registry.config().commonTags(
                "cf_app_name", applicationName, //
                "cf_app_instance_id", instanceId //
        );
    }
}
