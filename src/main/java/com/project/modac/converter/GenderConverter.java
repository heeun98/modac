package com.project.modac.converter;

import com.project.modac.domain.Gender;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class GenderConverter implements Converter<String, Gender> {
    @Override
    public Gender convert(String source) {
        return Gender.fromGender(source);
    }
}
