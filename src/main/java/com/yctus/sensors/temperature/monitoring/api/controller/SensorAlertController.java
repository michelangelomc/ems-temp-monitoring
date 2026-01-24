package com.yctus.sensors.temperature.monitoring.api.controller;

import com.yctus.sensors.temperature.monitoring.api.model.SensorAlertRequest;
import com.yctus.sensors.temperature.monitoring.api.model.SensorAlertResponse;
import com.yctus.sensors.temperature.monitoring.domain.model.SensorAlert;
import com.yctus.sensors.temperature.monitoring.domain.model.SensorId;
import com.yctus.sensors.temperature.monitoring.domain.repository.SensorAlertRepository;
import io.hypersistence.tsid.TSID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/sensors/{id}/alerts")
@RequiredArgsConstructor
public class SensorAlertController {

    private final SensorAlertRepository sensorAlertRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SensorAlertResponse create(@PathVariable TSID id, @RequestBody SensorAlertRequest sensorAlertRequest) {
        SensorId idSensor = new SensorId(id);
        SensorAlert sensorAlert = SensorAlert.builder()
                .id(idSensor)
                .maxTemperature(sensorAlertRequest.getMaxTemperature())
                .minTemperature(sensorAlertRequest.getMinTemperature())
                .build();

        log.info("Dados para salvar: id {}, value {}", id,sensorAlert.toString());

        sensorAlertRepository.saveAndFlush(sensorAlert);

        return SensorAlertResponse.builder()
                .sensorId(sensorAlert.getId().getId())
                .maxTemperature(sensorAlert.getMaxTemperature())
                .minTemperature(sensorAlert.getMinTemperature())
                .build();

    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public SensorAlertResponse update(@PathVariable TSID id, @RequestBody SensorAlertRequest sensorAlertRequest) {
        try {
            SensorAlert sensorAlert = sensorAlertRepository.findById(new SensorId(id))
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

            SensorAlert sensorUpdated = SensorAlert.builder()
                    .id(sensorAlert.getId())
                    .minTemperature(sensorAlertRequest.getMinTemperature())
                    .maxTemperature(sensorAlertRequest.getMaxTemperature())
                    .build();

            sensorAlertRepository.saveAndFlush(sensorUpdated);

            return SensorAlertResponse.builder()
                    .sensorId(sensorUpdated.getId().getId())
                    .maxTemperature(sensorUpdated.getMaxTemperature())
                    .minTemperature(sensorUpdated.getMinTemperature())
                    .build();
        } catch (Exception ex) {
            log.info(ex.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String delete(@PathVariable TSID id) {
        try {
            SensorAlert sensorAlert = sensorAlertRepository.findById(new SensorId(id))
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

            sensorAlertRepository.delete(sensorAlert);

            return String.format("id %s has deleted", id);
        } catch (Exception ex) {
            log.info(ex.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public SensorAlertResponse search(@PathVariable TSID id, @PageableDefault Pageable pageable) {
        SensorAlert sensorAlert = sensorAlertRepository.findById(new SensorId(id))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return SensorAlertResponse.builder()
                .sensorId(sensorAlert.getId().getId())
                .maxTemperature(sensorAlert.getMaxTemperature())
                .minTemperature(sensorAlert.getMinTemperature())
                .build();
    }
}