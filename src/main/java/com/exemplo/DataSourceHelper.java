package com.exemplo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Classe utilitária centralizada para gerenciar a alternância de DataSource.
 * Esta classe deve ser usada tanto pelos cadastros quanto pelos relatórios
 * para garantir consistência no acesso ao banco de dados.
 */
@Component
public class DataSourceHelper {

    private static final Logger logger = LoggerFactory.getLogger(DataSourceHelper.class);

    @Autowired
    private EmpresaRepository empresaRepository;
    
    @Autowired
    private DynamicDataSourceSwitcher dataSourceSwitcher;
    
    /**
     * Configura o DataSource para a empresa do usuário atual.
     * Este método deve ser chamado antes de qualquer operação de banco de dados.
     */
    public void configurarDataSourceAtual() {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null && auth.getPrincipal() instanceof CustomUserDetails) {
                CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
                Integer cdEmpresa = userDetails.getCdEmpresa();
                
                if (cdEmpresa != null) {
                    logger.debug("Configurando DataSource para empresa {}", cdEmpresa);
                    
                    Optional<Empresa> empresaOpt = empresaRepository.findByCd_empresa(cdEmpresa.intValue());
                    if (empresaOpt.isPresent()) {
                        Empresa empresa = empresaOpt.get();
                        // Alternar para o DataSource da empresa
                        dataSourceSwitcher.switchTo(empresa);
                        logger.debug("✅ DataSource alternado para empresa: {}", empresa.getCdEmpresa());
                    } else {
                        logger.warn("⚠️ Empresa não encontrada para cdEmpresa: {}", cdEmpresa);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("❌ Erro ao configurar DataSource: {}", e.getMessage(), e);
        }
    }
}
