package com.yctus.sensors.temperature.monitoring.api.config.jpa;

import io.hypersistence.tsid.TSID;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TSIDToLongJPAConverter implements AttributeConverter<TSID, Long> {

    @Override
    public Long convertToDatabaseColumn(TSID tsid) {
        if (tsid != null) return tsid.toLong();
        throw new IllegalArgumentException("TSID cannot be null");
    }

    @Override
    public TSID convertToEntityAttribute(Long dbData) {
        if (dbData != null) {
            return TSID.from(dbData);
        }
        throw new IllegalArgumentException("Database value for TSID cannot be null");
    }
}
