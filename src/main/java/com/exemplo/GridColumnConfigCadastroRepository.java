package com.exemplo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GridColumnConfigCadastroRepository extends JpaRepository<GridColumnConfigCadastro, Long> {

    List<GridColumnConfigCadastro> findByClassName(String className);
}