package com.project.modac.converter;

import com.project.modac.domain.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter implements Converter<String, Category> {

    @Override
    public Category convert(String source) {
        return Category.fromDisplayName(source);
    }

}