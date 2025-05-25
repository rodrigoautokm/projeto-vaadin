package com.exemplo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private UsuarioAcessoRepository usuarioAcessoRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private EmpresaAwareService empresaAwareService;

    @Override
    @Transactional(transactionManager = "primaryTransactionManager")
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Tentando autenticar usuário: '{}'", username);

        try {
            logger.info("Testando conexão com o banco principal (autokm) antes de buscar usuário...");
            Optional<UsuarioAcesso> testQuery = usuarioAcessoRepository.findByUsuario("test_connection");
            logger.info("Conexão com o banco principal testada com sucesso. Resultado da consulta de teste: {}", testQuery.isPresent());

            Optional<UsuarioAcesso> usuarioOptional = usuarioAcessoRepository.findByUsuario(username);
            if (usuarioOptional.isEmpty()) {
                logger.warn("Usuário não encontrado: '{}'", username);
                throw new UsernameNotFoundException("Usuário não encontrado: " + username);
            }

            UsuarioAcesso usuario = usuarioOptional.get();
            logger.info("Usuário encontrado: '{}', cdEmpresa: '{}', senha: '{}'", username, usuario.getCdEmpresa(), usuario.getSenha());

            Integer cdEmpresa = usuario.getCdEmpresa();
            if (cdEmpresa == null) {
                logger.warn("Usuário não associado a uma empresa: '{}'", username);
                throw new UsernameNotFoundException("Usuário não associado a uma empresa: " + username);
            }

            empresaAwareService.setCdEmpresa(cdEmpresa);
            logger.info("cdEmpresa atualizado no EmpresaAwareService: '{}'", cdEmpresa);

            logger.info("Buscando empresa para cdEmpresa: '{}'", cdEmpresa);
            Optional<Empresa> empresaOptional = empresaRepository.findByCd_empresa(cdEmpresa.intValue());
            if (empresaOptional.isEmpty()) {
                logger.warn("Empresa não encontrada para cdEmpresa: '{}'", cdEmpresa);
                throw new UsernameNotFoundException("Empresa não encontrada para cdEmpresa: " + cdEmpresa);
            }

            Empresa empresa = empresaOptional.get();
            logger.info("Empresa encontrada: cd_empresa: '{}', server_name: '{}', nome_banco: '{}'", empresa.getCd_empresa(), empresa.getServerName(), empresa.getNomeBanco());

            // Configura a conexão da empresa
            EmpresaConnectionManager.configure(empresa);

            String[] roles = usuario.getAdmin() != null && "S".equalsIgnoreCase(usuario.getAdmin())
                ? new String[]{"ADMIN"}
                : new String[]{"USER"};
            CustomUserDetails userDetails = new CustomUserDetails(
                username,
                usuario.getSenha(),
                cdEmpresa,
                Arrays.stream(roles)
                      .map(SimpleGrantedAuthority::new)
                      .collect(Collectors.toList())
            );
            userDetails.setEmpresa(empresa);
            logger.info("CustomUserDetails criado para usuário '{}': cdEmpresa={}, roles={}", username, cdEmpresa, roles);

            return userDetails;
        } catch (Exception e) {
            logger.error("Erro durante a autenticação do usuário '{}': {}", username, e.getMessage(), e);
            throw new UsernameNotFoundException("Erro durante a autenticação: " + e.getMessage(), e);
        }
    }
}
