package com.exemplo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColumnConfigCadastroRepository extends JpaRepository<ColumnConfigCadastroEntity, Integer> {
    List<ColumnConfigCadastroEntity> findByClassName(String className);
    ColumnConfigCadastroEntity findByClassNameAndFieldName(String className, String fieldName);
}