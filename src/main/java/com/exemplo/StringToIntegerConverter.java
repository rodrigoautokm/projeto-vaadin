package com.exemplo;

import com.vaadin.flow.data.converter.Converter;
import com.vaadin.flow.data.binder.Result;
import com.vaadin.flow.data.binder.ValueContext;

public class StringToIntegerConverter implements Converter<String, Integer> {

    private final String errorMessage;

    public StringToIntegerConverter(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public Result<Integer> convertToModel(String value, ValueContext context) {
        if (value == null || value.trim().isEmpty()) {
            return Result.ok(null); // Permite valores nulos
        }
        try {
            return Result.ok(Integer.valueOf(value));
        } catch (NumberFormatException e) {
            return Result.error(errorMessage);
        }
    }

    @Override
    public String convertToPresentation(Integer value, ValueContext context) {
        if (value == null) {
            return "";
        }
        return value.toString();
    }
}