package com.exemplo;

import com.vaadin.flow.component.combobox.ComboBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Component
public class GenericComboBoxFactory {

    private static final Logger logger = LoggerFactory.getLogger(GenericComboBoxFactory.class);

    private final MarcaService marcaService;

    @Autowired
    public GenericComboBoxFactory(MarcaService marcaService) {
        this.marcaService = marcaService;
        logger.info("Inicializando GenericComboBoxFactory");
    }

    @SuppressWarnings("unchecked")
    public <T> ComboBox<T> createComboBox(String label, String entityName, String dropdownValues, Map<String, String> dropdownValueMap) {
        logger.debug("Criando ComboBox com rótulo: {}, entityName: {}, dropdownValues: {}, dropdownValueMap: {}", label, entityName, dropdownValues, dropdownValueMap);
        ComboBox<T> comboBox = new ComboBox<>(label);
        comboBox.setPlaceholder("Selecione...");
        comboBox.setClearButtonVisible(true);
        comboBox.setAllowCustomValue(false);

        if (dropdownValues != null && !dropdownValues.isEmpty()) {
            if (dropdownValueMap != null && !dropdownValueMap.isEmpty()) {
                // Dropdown com valores fixos (usando mapeamento)
                List<String> rawValues = new ArrayList<>(dropdownValueMap.keySet());
                List<String> displayValues = new ArrayList<>(dropdownValueMap.values());
                logger.debug("Valores brutos para dropdown: {}", rawValues);
                logger.debug("Valores exibidos para dropdown: {}", displayValues);
                comboBox.setItems((List<T>) displayValues);
            } else {
                // Dropdown com valores fixos (sem mapeamento, retrocompatibilidade)
                List<String> items = Arrays.asList(dropdownValues.split(";"));
                logger.debug("Valores fixos para dropdown: {}", items);
                comboBox.setItems((List<T>) items);
            }
        } else if (entityName != null && !entityName.isEmpty()) {
            // Dropdown baseado em entidade
            if ("Marca".equals(entityName)) {
                List<Marca> items = marcaService.listar();
                comboBox.setItemLabelGenerator(item -> ((Marca) item).getDsMarca());
                comboBox.setItems(query -> {
                    String filter = query.getFilter().orElse("").toLowerCase();
                    Stream<Marca> filteredStream = items.stream()
                            .filter(marca -> marca.getDsMarca().toLowerCase().contains(filter) ||
                                    String.valueOf(marca.getCdMarca()).contains(filter));
                    return (Stream<T>) filteredStream;
                });
                comboBox.setItems((List<T>) items);
            } else {
                logger.warn("Entidade desconhecida para dropdown: {}. Retornando ComboBox vazio.", entityName);
                return comboBox;
            }
            logger.debug("Itens carregados para entidade {}: {}", entityName, marcaService.listar().size());
        } else {
            logger.warn("Nem dropdown_values nem entityName especificados. Retornando ComboBox vazio.");
            return comboBox;
        }

        comboBox.setWidth("100%");
        comboBox.getStyle().set("font-size", "var(--lumo-font-size-m)");
        return comboBox;
    }
}