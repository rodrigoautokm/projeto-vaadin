package com.exemplo;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ColumnConfigUsuarioRepository extends JpaRepository<ColumnConfigUsuario, Integer> {

    List<ColumnConfigUsuario> findByUsuarioAndClassName(String usuario, String className);

    List<ColumnConfigUsuario> findByUsuarioAndClassNameAndFieldName(String usuario, String className, String fieldName);

    @Modifying
    @Transactional
    @Query("UPDATE ColumnConfigUsuario c SET c.filtroAplicado = NULL WHERE c.usuario = :usuario AND c.className = :className AND c.fieldName = :fieldName")
    void limparFiltro(@Param("usuario") String usuario, @Param("className") String className, @Param("fieldName") String fieldName);
}
