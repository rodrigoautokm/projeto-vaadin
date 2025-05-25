package com.exemplo;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Primary
public interface ColumnConfigPrincipalRepository extends JpaRepository<VwColumnConfigEntity, Integer> {
    List<VwColumnConfigEntity> findByClassName(String className);

    VwColumnConfigEntity findByClassNameAndFieldName(String className, String fieldName);

    @Query("SELECT c FROM VwColumnConfigEntity c WHERE c.className = 'default'")
    List<VwColumnConfigEntity> findByClassNameDefault();

    List<VwColumnConfigEntity> findByClassNameAndUsuario(String className, String usuario);

    @Query("SELECT c FROM VwColumnConfigEntity c WHERE c.className = :className AND c.usuario = 'default'")
    List<VwColumnConfigEntity> findByClassNameAndUsuarioIsDefault(String className);

    VwColumnConfigEntity findByClassNameAndFieldNameAndUsuario(String className, String fieldName, String usuario);

    @Query("SELECT c FROM VwColumnConfigEntity c WHERE c.className = :className AND c.fieldName = :fieldName AND c.usuario = 'default'")
    VwColumnConfigEntity findByClassNameAndFieldNameAndUsuarioIsDefault(String className, String fieldName);
}