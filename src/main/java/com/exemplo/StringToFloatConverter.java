package com.exemplo;

import com.vaadin.flow.data.converter.Converter;
import com.vaadin.flow.data.binder.Result;
import com.vaadin.flow.data.binder.ValueContext;

public class StringToFloatConverter implements Converter<String, Float> {

    private final String errorMessage;

    public StringToFloatConverter(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public Result<Float> convertToModel(String value, ValueContext context) {
        if (value == null || value.trim().isEmpty()) {
            return Result.ok(null);
        }
        try {
            return Result.ok(Float.valueOf(value));
        } catch (NumberFormatException e) {
            return Result.error(errorMessage);
        }
    }

    @Override
    public String convertToPresentation(Float value, ValueContext context) {
        if (value == null) {
            return "";
        }
        return value.toString();
    }
}