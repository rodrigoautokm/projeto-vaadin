package com.exemplo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UsuarioAcessoService {
    private static final Logger logger = LoggerFactory.getLogger(UsuarioAcessoService.class);

    private final UsuarioAcessoRepository usuarioAcessoRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioAcessoService(UsuarioAcessoRepository usuarioAcessoRepository, PasswordEncoder passwordEncoder) {
        this.usuarioAcessoRepository = usuarioAcessoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        logger.info("Inicializando UsuarioAcessoService...");

        try {
            logger.info("Testando conexão com o banco principal (autokm) antes de verificar usuários...");
            Optional<UsuarioAcesso> testQuery = usuarioAcessoRepository.findByUsuario("test_connection");
            logger.info("Conexão com o banco principal testada com sucesso. Resultado da consulta de teste: {}", testQuery.isPresent());

            logger.info("Verificando se o usuário 'admin' existe no banco principal (autokm)...");
            Optional<UsuarioAcesso> adminOpt = usuarioAcessoRepository.findByUsuario("admin");
            if (adminOpt.isEmpty()) {
                logger.info("Usuário 'admin' não encontrado. Criando...");
                createUsuarioAcesso("admin", "admin123", "S");
            } else {
                UsuarioAcesso admin = adminOpt.get();
                logger.info("Usuário 'admin' já existe. Username: '{}', Id: '{}', cdEmpresa: '{}'", 
                    admin.getUsuario(), admin.getId(), admin.getCdEmpresa());
            }
            logAllUsers();
        } catch (Exception e) {
            logger.error("Erro ao inicializar UsuarioAcessoService: {}", e.getMessage(), e);
            throw new RuntimeException("Falha ao inicializar UsuarioAcessoService: " + e.getMessage(), e);
        }
    }

    @Transactional(transactionManager = "primaryTransactionManager")
    public void createUsuarioAcesso(String usuario, String senha, String admin) {
        logger.info("Criando usuário: '{}'", usuario);
        try {
            UsuarioAcesso user = new UsuarioAcesso();
            user.setUsuario(usuario);
            user.setSenha(passwordEncoder.encode(senha));
            user.setDataCriacao(Timestamp.valueOf(LocalDateTime.now()));
            user.setDataAtualizacao(Timestamp.valueOf(LocalDateTime.now()));
            user.setAdmin(admin);
            user.setCdEmpresa((Integer) 1); // Definir cdEmpresa como 1
            usuarioAcessoRepository.save(user);
            logger.info("Usuário criado com sucesso: '{}'", usuario);
        } catch (Exception e) {
            logger.error("Erro ao criar usuário '{}': {}", usuario, e.getMessage(), e);
            throw new RuntimeException("Falha ao criar usuário: " + e.getMessage(), e);
        }
    }

    @Transactional(transactionManager = "primaryTransactionManager", readOnly = true)
    public void logAllUsers() {
        logger.info("Listando todos os usuários na tabela 'usuario_acesso':");
        try {
            Iterable<UsuarioAcesso> allUsers = usuarioAcessoRepository.findAll();
            if (!allUsers.iterator().hasNext()) {
                logger.warn("Nenhum usuário encontrado na tabela 'usuario_acesso'.");
            } else {
                allUsers.forEach(user -> logger.info(" - Username: '{}', Id: '{}', Admin: '{}', cdEmpresa: '{}'", 
                    user.getUsuario(), user.getId(), user.getAdmin(), user.getCdEmpresa()));
            }
        } catch (Exception e) {
            logger.error("Erro ao listar usuários existentes: {}", e.getMessage(), e);
            throw new RuntimeException("Erro ao listar usuários: " + e.getMessage(), e);
        }
    }

    @PostConstruct
    public void testPassword() {
        logger.info("Testando senha para admin...");
        try {
            Optional<UsuarioAcesso> adminOpt = usuarioAcessoRepository.findByUsuario("admin");
            if (adminOpt.isPresent()) {
                String storedPassword = adminOpt.get().getSenha();
                boolean matches = passwordEncoder.matches("admin123", storedPassword);
                logger.info("Senha 'admin123' corresponde ao hash '{}'? {}", storedPassword, matches);
            } else {
                logger.warn("Usuário admin não encontrado para teste de senha.");
            }
        } catch (Exception e) {
            logger.error("Erro ao testar senha do admin: {}", e.getMessage(), e);
            throw new RuntimeException("Erro ao testar senha do admin: " + e.getMessage(), e);
        }
    }

    @Transactional(transactionManager = "primaryTransactionManager", readOnly = true)
    public boolean authenticate(String usuario, String senha) {
        logger.info("Autenticando usuário: '{}', senha fornecida: '{}'", usuario, senha);
        try {
            Optional<UsuarioAcesso> userOpt = usuarioAcessoRepository.findByUsuario(usuario);
            logger.info("Resultado da busca: usuário encontrado? {}", userOpt.isPresent());
            if (userOpt.isPresent()) {
                UsuarioAcesso user = userOpt.get();
                logger.info("Senha armazenada: {}", user.getSenha());
                boolean matches = passwordEncoder.matches(senha, user.getSenha());
                logger.info("Senha corresponde? {}", matches);
                if (matches) {
                    logger.info("Autenticação bem-sucedida para o usuário: '{}'", usuario);
                    return true;
                } else {
                    logger.warn("Senha incorreta para o usuário: '{}'", usuario);
                    return false;
                }
            } else {
                logger.warn("Usuário não encontrado: '{}'", usuario);
                logAllUsers();
                return false;
            }
        } catch (Exception e) {
            logger.error("Erro ao autenticar usuário '{}': {}", usuario, e.getMessage(), e);
            throw new RuntimeException("Erro ao autenticar usuário: " + e.getMessage(), e);
        }
    }

    @Transactional(transactionManager = "primaryTransactionManager", readOnly = true)
    public Optional<UsuarioAcesso> findByUsuario(String usuario) {
        try {
            return usuarioAcessoRepository.findByUsuario(usuario);
        } catch (Exception e) {
            logger.error("Erro ao buscar usuário '{}': {}", usuario, e.getMessage(), e);
            throw new RuntimeException("Erro ao buscar usuário: " + e.getMessage(), e);
        }
    }
}