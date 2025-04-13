package com.exemplo.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioAcessoRepository extends JpaRepository<UsuarioAcesso, Integer> {
    @Query("SELECT COALESCE(MAX(id), 0) FROM UsuarioAcesso")
    Integer findMaxId();
}