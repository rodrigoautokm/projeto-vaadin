package com.exemplo;

import com.vaadin.flow.data.converter.Converter;
import com.vaadin.flow.data.binder.Result;
import com.vaadin.flow.data.binder.ValueContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class StringToLocalDateTimeConverter implements Converter<String, LocalDateTime> {

    private static final Logger logger = LoggerFactory.getLogger(StringToLocalDateTimeConverter.class);

    private final String errorMessage;
    private final DateTimeFormatter formatter;

    public StringToLocalDateTimeConverter(String errorMessage) {
        this.errorMessage = errorMessage;
        this.formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    }

    @Override
    public Result<LocalDateTime> convertToModel(String value, ValueContext context) {
        logger.debug("convertToModel: value={}", value);
        if (value == null || value.trim().isEmpty()) {
            logger.debug("convertToModel: valor nulo ou vazio, retornando null");
            return Result.ok(null);
        }
        try {
            LocalDateTime dateTime = LocalDateTime.parse(value, formatter);
            logger.debug("convertToModel: convertido para LocalDateTime: {}", dateTime);
            return Result.ok(dateTime);
        } catch (DateTimeParseException e) {
            logger.error("convertToModel: erro ao converter '{}': {}", value, e.getMessage());
            return Result.error(errorMessage);
        }
    }

    @Override
    public String convertToPresentation(LocalDateTime value, ValueContext context) {
        logger.debug("convertToPresentation: valor original={}", value);
        if (value == null) {
            logger.debug("convertToPresentation: valor nulo, retornando vazio");
            return "";
        }
        String formattedDateTime = formatter.format(value);
        logger.debug("convertToPresentation: valor formatado={}", formattedDateTime);
        return formattedDateTime;
    }
}