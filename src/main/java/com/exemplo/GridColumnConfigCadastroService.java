package com.exemplo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GridColumnConfigCadastroService {

    private static final Logger logger = LoggerFactory.getLogger(GridColumnConfigCadastroService.class);

    @Autowired
    private GridColumnConfigCadastroRepository repository;

    public List<GridColumnConfigCadastro> getAllConfigs(String className) {
        logger.debug("Buscando todas as configurações para className='{}'", className);
        List<GridColumnConfigCadastro> configs = repository.findByClassName(className);
        logger.debug("Configurações encontradas para className='{}': {}", className, configs.size());
        return configs;
    }

    public GridColumnConfigCadastro getColumnConfig(String className, String fieldName) {
        logger.debug("Buscando configuração para className='{}', fieldName='{}'", className, fieldName);
        List<GridColumnConfigCadastro> configs = repository.findByClassName(className);
        logger.debug("Configurações encontradas para className='{}': {}", className, configs.size());
        configs.forEach(config -> logger.debug("Configuração: fieldName='{}', alias='{}', header='{}'", 
            config.getField(), config.getAlias(), config.getHeader()));

        Optional<GridColumnConfigCadastro> config = configs.stream()
            .filter(c -> {
                String fieldToCompare = (c.getAlias() != null && !c.getAlias().isEmpty()) ? c.getAlias() : c.getField();
                return fieldToCompare != null && fieldToCompare.trim().equalsIgnoreCase(fieldName.trim());
            })
            .findFirst();

        if (config.isPresent()) {
            logger.debug("Configuração encontrada: {}", config.get());
        } else {
            logger.warn("Nenhuma configuração encontrada para className='{}', fieldName='{}'", className, fieldName);
        }
        return config.orElse(null);
    }
}