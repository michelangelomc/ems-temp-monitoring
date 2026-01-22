package com.yctus.sensors.temperature.monitoring.domain.model;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Data
@Entity
@Builder
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SensorAlert implements Serializable {
    @Serial
    private static final long serialVersionUID = 8525554157780940511L;

    @Id
    @AttributeOverride(name = "id", column = @Column(name = "id", columnDefinition = "BIGINT"))
    private SensorId id;

    private double maxTemperature;
    private double minTemperature;
}
