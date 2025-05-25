package com.exemplo.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioAcessoService {
    private static final Logger logger = LoggerFactory.getLogger(UsuarioAcessoService.class);

    @Autowired
    private UsuarioAcessoRepository usuarioAcessoRepository;

    @Autowired
    private PasswordEncoderService passwordEncoder;

    public void cadastrarUsuario(UsuarioAcesso usuario) {
        logger.info("Cadastrando usuário de acesso: '{}'", usuario.getUsuario());
        try {
            String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
            usuario.setSenha(senhaCriptografada);

            // Definir o id manualmente
            Integer maxId = usuarioAcessoRepository.findMaxId();
            usuario.setId(maxId + 1);

            usuarioAcessoRepository.save(usuario);
            logger.info("Usuário de acesso cadastrado com sucesso: '{}'", usuario.getUsuario());
        } catch (Exception e) {
            logger.error("Erro ao cadastrar usuário de acesso '{}': {}", usuario.getUsuario(), e.getMessage(), e);
            throw new RuntimeException("Falha ao cadastrar usuário de acesso: " + e.getMessage(), e);
        }
    }
}