package com.exemplo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VwColumnConfigRepository extends JpaRepository<VwColumnConfigEntity, Integer> {

    List<VwColumnConfigEntity> findAll();

    List<VwColumnConfigEntity> findByClassName(String className);

    @Query("SELECT c FROM VwColumnConfigEntity c WHERE c.className = :className")
    List<VwColumnConfigEntity> findByClassNameAndUsuarioIsDefault(@Param("className") String className);

    @Query("SELECT c FROM VwColumnConfigEntity c WHERE c.className = 'default'")
    List<VwColumnConfigEntity> findByClassNameDefault();

    @Query("SELECT c FROM VwColumnConfigEntity c WHERE c.usuario = :usuario AND c.className = :className")
    List<VwColumnConfigEntity> findByUsuarioAndClassName(@Param("usuario") String usuario, @Param("className") String className);
}