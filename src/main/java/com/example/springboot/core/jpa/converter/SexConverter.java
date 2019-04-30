package com.example.springboot.core.jpa.converter;

import com.example.springboot.core.enumeration.SexEnum;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class SexConverter implements AttributeConverter<SexEnum, Integer> {
    @Override
    public Integer convertToDatabaseColumn(SexEnum sexEnum) {
        return sexEnum.getId();
    }

    @Override
    public SexEnum convertToEntityAttribute(Integer id) {
        return SexEnum.getEnumById(id);
    }
}
