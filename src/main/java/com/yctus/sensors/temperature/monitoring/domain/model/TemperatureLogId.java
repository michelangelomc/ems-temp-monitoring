package com.yctus.sensors.temperature.monitoring.domain.model;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
public class TemperatureLogId implements Serializable {

    @Serial
    private static final long serialVersionUID = -1010625192547603812L;
    private UUID id;

    public TemperatureLogId(UUID id) {
        this.id = id;
    }

    public TemperatureLogId(String id) {
        this.id = UUID.fromString(id);
    }

    @Override
    public String toString() {
        return "TemperatureLogId{" +
                "uuid=" + id +
                '}';
    }
}
