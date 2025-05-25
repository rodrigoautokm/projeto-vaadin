package com.exemplo;

import com.vaadin.flow.data.converter.Converter;
import com.vaadin.flow.data.binder.Result;
import com.vaadin.flow.data.binder.ValueContext;

public class StringToShortConverter implements Converter<String, Short> {

    private final String errorMessage;

    public StringToShortConverter(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public Result<Short> convertToModel(String value, ValueContext context) {
        if (value == null || value.trim().isEmpty()) {
            return Result.ok(null); // Permite valores nulos
        }
        try {
            return Result.ok(Short.valueOf(value));
        } catch (NumberFormatException e) {
            return Result.error(errorMessage);
        }
    }

    @Override
    public String convertToPresentation(Short value, ValueContext context) {
        if (value == null) {
            return "";
        }
        return value.toString();
    }
}