package com.exemplo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Service
public class VendasService {

    private static final Logger logger = LoggerFactory.getLogger(VendasService.class);

    @Autowired
    private DataSource dataSource;

    public List<VendasItem> executarRelVendas(int param1, String dataInicio, String dataFim, int param4, int param5,
                                             int param6, int param7, int param8, int param9, int param10, int param11,
                                             int param12, String param13, int param14, String param15, String param16,
                                             int param17, String param18, String param19, int param20) {
        List<VendasItem> itens = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             CallableStatement stmt = conn.prepareCall("{call pr_rel_vendas_r04(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}")) {

            logger.info("Executando procedure pr_rel_vendas_r04 com parâmetros: dataInicio={}, dataFim={}", dataInicio, dataFim);

            stmt.setInt(1, param1);
            stmt.setString(2, dataInicio);
            stmt.setString(3, dataFim);
            stmt.setInt(4, param4);
            stmt.setInt(5, param5);
            stmt.setInt(6, param6);
            stmt.setInt(7, param7);
            stmt.setInt(8, param8);
            stmt.setInt(9, param9);
            stmt.setInt(10, param10);
            stmt.setInt(11, param11);
            stmt.setInt(12, param12);
            stmt.setString(13, param13);
            stmt.setInt(14, param14);
            stmt.setString(15, param15);
            stmt.setString(16, param16);
            stmt.setInt(17, param17);
            stmt.setString(18, param18);
            stmt.setString(19, param19);
            stmt.setInt(20, param20);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                VendasItem item = new VendasItem(
                    rs.getString("nr_pedido"),
                    rs.getString("nm_cliente"),
                    rs.getString("ds_produto"),
                    rs.getDouble("qt_produto"),
                    rs.getDouble("vl_unitario"),
                    rs.getDouble("vl_desconto"),
                    rs.getDouble("vl_custo"),
                    rs.getString("dt_emissao"),
                    rs.getString("situacao"),
                    rs.getString("ds_grupo")
                );
                itens.add(item);
            }
            logger.info("Consulta retornou {} itens.", itens.size());

        } catch (Exception e) {
            logger.error("Erro ao executar a procedure pr_rel_vendas_r04: {}", e.getMessage(), e);
        }

        return itens;
    }
}