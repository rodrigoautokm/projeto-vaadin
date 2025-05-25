package com.exemplo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class ColumnAliasService {

    @Autowired
    private GridColumnConfigService gridColumnConfigService;

    public void configureColumnAliases(List<GridColumnConfig> columnConfigs) {
        for (GridColumnConfig config : columnConfigs) {
            String alias = config.getAlias();  // Aqui pegamos o alias

            // Aqui configuramos o ValueProvider corretamente
            if (alias != null && !alias.isEmpty()) {
                // Usamos o alias para mapear para a coluna
                config.setAlias(alias);
            }
        }
    }
}
