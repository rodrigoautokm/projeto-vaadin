package com.exemplo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface ListagemPedidoRepository extends JpaRepository<ListagemPedido, ListagemPedidoId> {

    @Query(value = "SELECT DISTINCT " +
            "pv.cd_empresa, pv.nr_pedido, pv.serie, pv.cd_empresa_venda, pv.dt_emissao, " +
            "pv.cd_cliente, pv.nm_cliente, pv.vl_total, pv.vl_desconto_total, pv.situacao, " +
            "pv.cd_funcionario, f.nm_funcionario " +
            "FROM pedido_venda pv " +
            "LEFT OUTER JOIN funcionario f ON f.cd_funcionario = pv.cd_funcionario " +
            "WHERE pv.dt_emissao BETWEEN :dataInicio AND :dataFim",
            nativeQuery = true)
    List<ListagemPedido> findByPeriodo(
        @Param("dataInicio") Timestamp dataInicio,
        @Param("dataFim") Timestamp dataFim
    );
}