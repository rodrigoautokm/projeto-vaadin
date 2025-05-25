package com.exemplo;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.combobox.ComboBox;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ParametroDialogBuilder {

    private final String title;
    private final List<ParametroRelatorio> parametros;
    private final Consumer<Map<String, Object>> callback;
    private final boolean camposRetornoConfigurados;

    public ParametroDialogBuilder(String title, List<ParametroRelatorio> parametros, Consumer<Map<String, Object>> callback, boolean camposRetornoConfigurados) {
        this.title = title;
        this.parametros = parametros;
        this.callback = callback;
        this.camposRetornoConfigurados = camposRetornoConfigurados;
    }

    public void abrir() {
        Dialog dialog = new Dialog();
        dialog.setHeaderTitle(title);
        dialog.setModal(true);
        dialog.setWidth("400px");

        VerticalLayout layout = new VerticalLayout();
        layout.setPadding(false);
        layout.setSpacing(true);

        if (!camposRetornoConfigurados) {
            Span aviso = new Span("⚠️ Campos de retorno não configurados. Eles serão populados automaticamente após a primeira execução.");
            aviso.getStyle().set("color", "red");
            layout.add(aviso);
        }

        Map<String, Object> valores = new HashMap<>();

        for (ParametroRelatorio p : parametros) {
            String field = p.getField();
            String tipo = p.getTipo();
            String header = p.getHeader();
            String valorDefault = p.getValorDefault();

            if ("NUMBER".equalsIgnoreCase(tipo)) {
                TextField fieldInput = new TextField(header);
                fieldInput.setValue(valorDefault != null ? valorDefault : "0");
                fieldInput.addValueChangeListener(e -> valores.put(field, parseNumber(e.getValue())));
                layout.add(fieldInput);

            } else if ("DATE".equalsIgnoreCase(tipo) || "DATETIME".equalsIgnoreCase(tipo)) {
                DatePicker datePicker = new DatePicker(header);
                if (valorDefault != null && !valorDefault.isEmpty()) {
                    try {
                        LocalDate date = LocalDate.parse(valorDefault.split(" ")[0], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        datePicker.setValue(date);
                    } catch (Exception e) {
                        // Ignorar erro no valor default
                    }
                }
                datePicker.addValueChangeListener(e -> {
                    if (e.getValue() != null) {
                        // Não salva direto aqui — salva no botão "Aplicar" com horário correto
                        valores.put(field, e.getValue());
                    } else {
                        valores.put(field, null);
                    }
                });
                layout.add(datePicker);

            } else {
                String dropdownValues = p.getDropdownValues();
                if (dropdownValues != null && !dropdownValues.isEmpty()) {
                    List<String> options = parseDropdownValues(dropdownValues);
                    ComboBox<String> comboBox = new ComboBox<>(header);
                    comboBox.setItems(options);
                    if (valorDefault != null && !valorDefault.isEmpty()) {
                        comboBox.setValue(valorDefault);
                    } else if (!options.isEmpty()) {
                        comboBox.setValue(options.get(0));
                    }
                    comboBox.addValueChangeListener(e -> valores.put(field, e.getValue()));
                    layout.add(comboBox);
                } else {
                    TextField fieldInput = new TextField(header);
                    fieldInput.setValue(valorDefault != null ? valorDefault : "");
                    fieldInput.addValueChangeListener(e -> valores.put(field, e.getValue()));
                    layout.add(fieldInput);
                }
            }
        }

        Button applyButton = new Button("Aplicar", e -> {
            Map<String, Object> parametrosFinal = new HashMap<>();

            for (Map.Entry<String, Object> entry : valores.entrySet()) {
                String chave = entry.getKey();
                Object valor = entry.getValue();

                if (valor instanceof LocalDate) {
                    if (chave.equalsIgnoreCase("dt_inicio")) {
                        parametrosFinal.put(chave, ((LocalDate) valor).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + " 00:00:00.000");
                    } else if (chave.equalsIgnoreCase("dt_fim")) {
                        parametrosFinal.put(chave, ((LocalDate) valor).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + " 23:59:59.999");
                    } else {
                        parametrosFinal.put(chave, ((LocalDate) valor).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                    }
                } else {
                    parametrosFinal.put(chave, valor);
                }
            }

            callback.accept(parametrosFinal);
            dialog.close();
        });

        Button closeButton = new Button("Fechar", e -> dialog.close());

        dialog.getFooter().add(closeButton, applyButton);
        dialog.add(layout);
        dialog.open();
    }

    private List<String> parseDropdownValues(String dropdownValues) {
        if (dropdownValues == null || dropdownValues.isEmpty()) {
            return List.of();
        }
        String cleanedValues = dropdownValues.replace("'", "");
        return Arrays.stream(cleanedValues.split(","))
                     .map(String::trim)
                     .filter(v -> !v.isEmpty())
                     .collect(Collectors.toList());
    }

    private Integer parseNumber(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
