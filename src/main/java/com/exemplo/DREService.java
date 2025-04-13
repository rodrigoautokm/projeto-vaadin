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
public class DREService {

    private static final Logger logger = LoggerFactory.getLogger(DREService.class);

    @Autowired
    private DataSource dataSource;

    public List<DREItem> executarDRE(int param1, String dataInicio, String dataFim, int param4, 
                                     String param5, String param6, String param7, String param8, 
                                     String param9, String param10, String param11) {
        List<DREItem> itens = new ArrayList<>();
        
        try (Connection conn = dataSource.getConnection();
             CallableStatement stmt = conn.prepareCall("{call pr_rel_demonstrativo_resultado_arvore(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}")) {

            logger.info("Executando procedure com parâmetros: param1={}, dataInicio={}, dataFim={}, param4={}, param5={}, param6={}, param7={}, param8={}, param9={}, param10={}, param11={}", 
                        param1, dataInicio, dataFim, param4, param5, param6, param7, param8, param9, param10, param11);

            stmt.setInt(1, param1);
            stmt.setString(2, dataInicio);
            stmt.setString(3, dataFim);
            stmt.setInt(4, param4);
            stmt.setString(5, param5);
            stmt.setString(6, param6);
            stmt.setString(7, param7);
            stmt.setString(8, param8);
            stmt.setString(9, param9);
            stmt.setString(10, param10);
            stmt.setString(11, param11);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                DREItem item = new DREItem(
                    rs.getString("agrupa"),
                    rs.getString("numero"),
                    rs.getDouble("vl_titulo"),
                    rs.getString("nm_fornecedor"),
                    rs.getString("situacao"),
                    rs.getString("dt_emissao"),
                    rs.getString("dt_vencto"),
                    rs.getString("dt_pgto"),
                    rs.getString("obs"),
                    rs.getString("despesa1")
                );
                itens.add(item);
            }
            logger.info("Consulta retornou {} itens.", itens.size());

        } catch (Exception e) {
            logger.error("Erro ao executar a procedure: {}", e.getMessage(), e);
        }

        return itens;
    }
}