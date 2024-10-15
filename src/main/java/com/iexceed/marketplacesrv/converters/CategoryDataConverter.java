package com.iexceed.marketplacesrv.converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iexceed.marketplacesrv.model.Categories;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CategoryDataConverter implements AttributeConverter<Categories.CategoryData, String> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Categories.CategoryData categoryData) {
        try {
            return objectMapper.writeValueAsString(categoryData);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting CategoryData to JSON", e);
        }
    }

    @Override
    public Categories.CategoryData convertToEntityAttribute(String json) {
        try {
            return objectMapper.readValue(json, Categories.CategoryData.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting JSON to CategoryData", e);
        }
    }
}




