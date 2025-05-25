package com.exemplo;

import com.vaadin.flow.data.converter.Converter;
import com.vaadin.flow.data.binder.Result;
import com.vaadin.flow.data.binder.ValueContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToDateConverter implements Converter<String, Date> {

    private static final Logger logger = LoggerFactory.getLogger(StringToDateConverter.class);

    private final String errorMessage;
    private final SimpleDateFormat dateFormat;

    public StringToDateConverter(String errorMessage) {
        this.errorMessage = errorMessage;
        this.dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        this.dateFormat.setLenient(false);
    }

    @Override
    public Result<Date> convertToModel(String value, ValueContext context) {
        logger.debug("convertToModel: value={}", value);
        if (value == null || value.trim().isEmpty()) {
            logger.debug("convertToModel: valor nulo ou vazio, retornando null");
            return Result.ok(null);
        }
        try {
            Date date = dateFormat.parse(value);
            logger.debug("convertToModel: convertido para Date: {}", date);
            return Result.ok(date);
        } catch (Exception e) {
            logger.error("convertToModel: erro ao converter '{}': {}", value, e.getMessage());
            return Result.error(errorMessage);
        }
    }

    @Override
    public String convertToPresentation(Date value, ValueContext context) {
        logger.debug("convertToPresentation: valor original={}", value);
        if (value == null) {
            logger.debug("convertToPresentation: valor nulo, retornando vazio");
            return "";
        }
        // Verificar o tipo de Date e converter se necessário
        Date convertedDate = value;
        if (value instanceof java.sql.Date) {
            convertedDate = new java.util.Date(value.getTime());
            logger.debug("convertToPresentation: convertido de java.sql.Date para java.util.Date: {}", convertedDate);
        }
        String formattedDate = dateFormat.format(convertedDate);
        logger.debug("convertToPresentation: valor formatado={}", formattedDate);
        return formattedDate;
    }
}