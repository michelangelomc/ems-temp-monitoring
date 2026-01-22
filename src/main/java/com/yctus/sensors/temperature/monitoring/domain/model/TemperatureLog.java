package com.yctus.sensors.temperature.monitoring.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TemperatureLog {

    @Id
    @AttributeOverride(name = "id", column = @Column(name = "id", columnDefinition = "varchar(100)"))
    private TemperatureLogId id;

    @Column(name = "\"VALUE\"")
    private Double value;

    private OffsetDateTime registeredAt;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "sensor_id", columnDefinition = "bigint"))
    private SensorId sensorId;
}
