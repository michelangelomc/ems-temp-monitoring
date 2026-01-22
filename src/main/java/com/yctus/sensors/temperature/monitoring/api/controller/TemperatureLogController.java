package com.yctus.sensors.temperature.monitoring.api.controller;

import com.yctus.sensors.temperature.monitoring.api.model.TemperatureLogResponse;
import com.yctus.sensors.temperature.monitoring.domain.model.SensorId;
import com.yctus.sensors.temperature.monitoring.domain.model.TemperatureLog;
import com.yctus.sensors.temperature.monitoring.domain.repository.TemperatureLogRepository;
import io.hypersistence.tsid.TSID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sensors/{id}/temperatures")
@RequiredArgsConstructor
public class TemperatureLogController {

    private final TemperatureLogRepository temperatureLogRepository;

    @GetMapping
    public Page<TemperatureLogResponse> search(@PathVariable TSID id, @PageableDefault Pageable pageable) {
        Page<TemperatureLog> temptLog = temperatureLogRepository.findAllBySensorId(new SensorId(id), pageable);

        return temptLog.map(temp -> TemperatureLogResponse.builder()
                .id(temp.getId().getId())
                .sensorId(temp.getSensorId().getId())
                .registeredAt(temp.getRegisteredAt())
                .temperature(temp.getValue())
                .build());
    }
}
