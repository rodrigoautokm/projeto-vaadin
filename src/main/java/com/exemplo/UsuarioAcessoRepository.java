package com.exemplo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioAcessoRepository extends JpaRepository<UsuarioAcesso, Integer> {

    Optional<UsuarioAcesso> findByUsuario(String usuario);
}
