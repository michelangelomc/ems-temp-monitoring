package com.yctus.sensors.temperature.monitoring.api.model;

import lombok.Data;

@Data
public class SensorAlertRequest {
    private double maxTemperature;
    private double minTemperature;
}
