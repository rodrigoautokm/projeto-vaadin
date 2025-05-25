package com.exemplo.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
public class AdminUserService {
    private static final Logger logger = LoggerFactory.getLogger(AdminUserService.class);

    @Autowired
    private AdminUserRepository adminUserRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostConstruct
    public void init() {
        logger.info("Inicializando AdminUserService...");
        try {
            logger.info("Verificando se o usuário administrativo 'admin' existe...");
            Optional<AdminUser> adminOpt = adminUserRepository.findByUsername("admin");
            if (adminOpt.isEmpty()) {
                logger.info("Usuário 'admin' não encontrado. Criando...");
                createAdminUserTransactional("admin", "admin123");
            } else {
                AdminUser admin = adminOpt.get();
                logger.info("Usuário 'admin' já existe no banco. Username: '{}', Password hash: '{}', Id: '{}'", admin.getUsername(), admin.getPassword(), admin.getId());
            }
            // Verificar usuários existentes após a inicialização
            logAllUsers();
        } catch (Exception e) {
            logger.error("Erro ao inicializar usuário administrativo 'admin': {}", e.getMessage(), e);
            throw new RuntimeException("Falha ao inicializar usuário administrativo: " + e.getMessage(), e);
        }
    }

    @Transactional
    public void createAdminUserTransactional(String username, String password) {
        createAdminUser(username, password);
    }

    @Transactional(readOnly = true)
    public void logAllUsers() {
        logger.info("Listando todos os usuários na tabela 'admin_users':");
        try {
            Iterable<AdminUser> allUsers = adminUserRepository.findAll();
            if (!allUsers.iterator().hasNext()) {
                logger.warn("Nenhum usuário encontrado na tabela 'admin_users'.");
            } else {
                allUsers.forEach(user -> logger.info(" - Username: '{}', Password hash: '{}', Id: '{}'", user.getUsername(), user.getPassword(), user.getId()));
            }
        } catch (Exception e) {
            logger.error("Erro ao listar usuários existentes: {}", e.getMessage(), e);
        }
    }

    @Transactional(readOnly = true)
    public boolean authenticate(String username, String password) {
        logger.info("Autenticando usuário: '{}'", username);
        try {
            logger.debug("Executando consulta findByUsername para usuário: '{}'", username);
            Optional<AdminUser> userOpt = adminUserRepository.findByUsername(username);
            if (userOpt.isPresent()) {
                AdminUser user = userOpt.get();
                logger.debug("Usuário encontrado: '{}', Hash da senha: '{}', Id: '{}'", user.getUsername(), user.getPassword(), user.getId());
                boolean matches = passwordEncoder.matches(password, user.getPassword());
                if (matches) {
                    logger.info("Autenticação bem-sucedida para o usuário: '{}'", username);
                } else {
                    logger.warn("Senha incorreta para o usuário: '{}'. Senha fornecida: '{}', Hash armazenado: '{}'", username, password, user.getPassword());
                    logger.debug("Verificando hash manualmente...");
                    String newHash = passwordEncoder.encode(password);
                    logger.debug("Novo hash gerado para a senha '{}': '{}'", password, newHash);
                }
                return matches;
            } else {
                logger.warn("Usuário não encontrado: '{}'. Verifique se o usuário existe na tabela 'dbo.admin_users'.", username);
                logAllUsers();
            }
            return false;
        } catch (Exception e) {
            logger.error("Erro ao autenticar usuário '{}': {}", username, e.getMessage(), e);
            return false;
        }
    }

    public void createAdminUser(String username, String password) {
        logger.info("Criando usuário administrativo: '{}'", username);
        try {
            AdminUser user = new AdminUser();
            user.setUsername(username);
            String encodedPassword = passwordEncoder.encode(password);
            logger.debug("Senha criptografada para '{}': '{}'", username, encodedPassword);
            user.setPassword(encodedPassword);

            // Definir o id manualmente
            Integer maxId = adminUserRepository.findMaxId();
            user.setId(maxId + 1);

            adminUserRepository.save(user);
            logger.info("Usuário administrativo criado com sucesso: '{}'", username);
        } catch (Exception e) {
            logger.error("Erro ao criar usuário administrativo '{}': {}", username, e.getMessage(), e);
            throw new RuntimeException("Falha ao criar usuário administrativo: " + e.getMessage(), e);
        }
    }
}