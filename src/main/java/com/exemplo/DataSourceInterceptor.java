package com.exemplo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Interceptor que garante que o DataSource correto seja configurado antes de cada requisição HTTP.
 * Esta versão simplificada usa o DataSourceHelper para centralizar a lógica de alternância.
 */
@Component
public class DataSourceInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(DataSourceInterceptor.class);

    @Autowired
    private DataSourceHelper dataSourceHelper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        logger.debug("Interceptando requisição: {}", request.getRequestURI());
        dataSourceHelper.configurarDataSourceAtual();
        return true; // Sempre continua o processamento da requisição
    }
}
