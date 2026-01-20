package com.yctus.sensors.temperature.monitoring.api.model;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class SensorRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -4376828934370291919L;

    private String name;
    private String ip;
    private String location;
    private String protocol;
    private String model;
    private Boolean enable;
}
