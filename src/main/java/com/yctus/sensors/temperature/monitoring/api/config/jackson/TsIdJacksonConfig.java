package com.yctus.sensors.temperature.monitoring.api.config.jackson;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;
import io.hypersistence.tsid.TSID;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TsIdJacksonConfig {
    @Bean
    public Module tsIdModule() {
        SimpleModule module = new SimpleModule();
        module.addSerializer(TSID.class, new TsIdToStringSerial());
        return module;
    }
}