package com.yctus.sensors.temperature.monitoring.api.client.controller;

import com.yctus.sensors.temperature.monitoring.api.client.model.SensorMonitoringResponse;
import com.yctus.sensors.temperature.monitoring.domain.model.SensorId;
import com.yctus.sensors.temperature.monitoring.domain.model.SensorMonitoring;
import com.yctus.sensors.temperature.monitoring.domain.repository.SensorMonitoringRepository;
import io.hypersistence.tsid.TSID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/sensors/{sensorId}/monitoring")
@RequiredArgsConstructor
public class SensorMonitoringController {
    private final SensorMonitoringRepository sensorMonitoringRepository;


    @GetMapping
    public SensorMonitoringResponse getDetail(@PathVariable TSID sensorId) {
        SensorMonitoring sensorMonitoring = getSensorMonitoring(sensorId);

        return SensorMonitoringResponse.builder()
                .id(sensorMonitoring.getId().getId())
                .lastTemperature(sensorMonitoring.getLastTemperature())
                .enabled(sensorMonitoring.getEnabled())
                .updatedAt(sensorMonitoring.getUpdatedAt())
                .build();
    }

    private SensorMonitoring getSensorMonitoring(TSID sensorId) {
        return sensorMonitoringRepository.findById(new SensorId(sensorId)).orElse(SensorMonitoring.builder()
                .id(new SensorId(sensorId))
                .lastTemperature(null)
                .enabled(false)
                .updatedAt(null)
                .build());
    }

    @PutMapping("/enabled")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void enableMonitoring(@PathVariable TSID sensorId) {
        SensorMonitoring sensorMonitoring = getSensorMonitoring(sensorId);
        sensorMonitoring.setEnabled(true);
        sensorMonitoringRepository.saveAndFlush(sensorMonitoring);
    }

    @PutMapping("/disable")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void disableMonitoring(@PathVariable TSID sensorId) {
        SensorMonitoring sensorMonitoring = getSensorMonitoring(sensorId);
        sensorMonitoring.setEnabled(false);
        sensorMonitoringRepository.saveAndFlush(sensorMonitoring);
    }
}