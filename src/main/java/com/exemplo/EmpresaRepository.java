package com.exemplo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {

    @Query("SELECT e FROM Empresa e WHERE e.cd_empresa = :cdEmpresa")
    Optional<Empresa> findByCd_empresa(@Param("cdEmpresa") Integer cdEmpresa);
}