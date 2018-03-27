package com.example.demomicrometer;

import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.config.MeterFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
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

    @Bean
    public TimedAspect timedAspect(MeterRegistry meterRegistry) {
        return new TimedAspect(meterRegistry);
    }

    @Bean
    public MeterFilter meterFilter() {
        return MeterFilter.deny(id -> {
            String uri = id.getTag("uri");
            return uri != null && uri.startsWith("/actuator");
        });
    }
}
