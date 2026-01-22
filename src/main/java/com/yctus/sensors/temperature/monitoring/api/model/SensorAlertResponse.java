package com.yctus.sensors.temperature.monitoring.api.model;

import io.hypersistence.tsid.TSID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SensorAlertResponse {
    private TSID sensorId;
    private double maxTemperature;
    private double minTemperature;
}
