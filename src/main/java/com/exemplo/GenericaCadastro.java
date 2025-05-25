package com.exemplo;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.Validator;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.data.binder.Result;
import com.vaadin.flow.data.converter.Converter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public abstract class GenericaCadastro<T> {

    private static final Logger logger = LoggerFactory.getLogger(GenericaCadastro.class);

    protected Dialog dialog;
    protected Binder<T> binder;
    protected T entity;
    protected final GenericRepository<T, ?> service;
    protected Consumer<T> onSaveCallback;
    protected List<FieldConfig<T>> fields;

    @Autowired
    protected GridColumnConfigCadastroService columnConfigService;

    @Autowired
    protected GenericComboBoxFactory comboBoxFactory;


    public GenericaCadastro(GenericRepository<T, ?> service, T entity, Consumer<T> onSaveCallback) {
        this.service = service;
        this.entity = entity;
        this.onSaveCallback = onSaveCallback;
        this.fields = new ArrayList<>();
    }

    @SuppressWarnings("unchecked")
    protected void initialize(T entity, Consumer<T> onSaveCallback) {
        this.entity = entity;
        this.onSaveCallback = onSaveCallback;
        this.fields.clear();
        this.binder = new Binder<>((Class<T>) entity.getClass());
        this.dialog = new Dialog();
        configureDialog();
        configureFields();
        buildForm();
    }

    protected void configureDialog() {
        dialog.setHeaderTitle(getTitle());
        dialog.setWidth("400px");
        dialog.setModal(true);
    }

    protected abstract String getTitle();

    protected abstract String getClassName();

    protected void configureFields() {
        logger.info("Configurando campos dinamicamente para entidade: {}", entity.getClass().getSimpleName());
        List<String> camposFixos = getCamposFixos();
        logger.debug("Campos fixos definidos: {}", camposFixos);

        String className = getClassName();
        String usuarioId = getUsuarioId();
        if (usuarioId == null) {
            usuarioId = "default";
        }

        List<GridColumnConfigCadastro> allConfigs = columnConfigService.getAllConfigs(className);
        logger.debug("Todas as configurações encontradas para className='{}': {}", className, allConfigs.size());
        allConfigs.forEach(config -> logger.debug("Configuração: field='{}', alias='{}', header='{}', visible={}", 
            config.getField(), config.getAlias(), config.getHeader(), config.isVisible()));

        for (String fieldName : camposFixos) {
            GridColumnConfigCadastro config = columnConfigService.getColumnConfig(className, fieldName);
            if (config == null) {
                logger.warn("Configuração não encontrada para o campo: {}. Ignorando.", fieldName);
                continue;
            }

            if (!config.isVisible()) {
                logger.info("Campo '{}' não será adicionado ao formulário porque não está visível", fieldName);
                continue;
            }

            String label = config.getHeader() != null ? config.getHeader() : fieldName;
            String propertyPath = resolvePropertyPath(config, fieldName);
            String dataType = config.getType() != null ? config.getType().toUpperCase() : "STRING";
            boolean required = config.isRequired();

            if (config.getDropdownValues() != null && !config.getDropdownValues().isEmpty()) {
                ComboBox<String> comboBox = comboBoxFactory.createComboBox(label, null, config.getDropdownValues(), config.getDropdownValueMap());
                addComboBoxFieldWithBinding(comboBox, fieldName, propertyPath, required, null, config.getDropdownValueMap());
            } else if (config.getDropdownEntity() != null && !config.getDropdownEntity().isEmpty()) {
                ComboBox<?> comboBox = comboBoxFactory.createComboBox(label, config.getDropdownEntity(), null, null);
                addComboBoxFieldWithBinding(comboBox, fieldName, propertyPath, required, config.getDropdownEntity(), null);
            } else {
                Component field = createFieldComponent(config, label);
                addFieldWithBinding(field, fieldName, propertyPath, dataType, required);
            }
        }

        logger.info("Campos configurados: {}", fields.size());
    }

    private String resolvePropertyPath(GridColumnConfigCadastro config, String fieldName) {
        String nestedColumns = config.getNestedColumns();
        if (nestedColumns != null && !nestedColumns.isEmpty()) {
            String resolvedPath = toCamelCase(nestedColumns) + "." + toCamelCase(fieldName);
            logger.debug("Resolvendo propertyPath: nestedColumns={}, fieldName={}, resolvedPath={}", nestedColumns, fieldName, resolvedPath);
            return resolvedPath;
        }
        String resolvedPath = toCamelCase(fieldName);
        logger.debug("Resolvendo propertyPath: fieldName={}, resolvedPath={}", fieldName, resolvedPath);
        return resolvedPath;
    }

    private Component createFieldComponent(GridColumnConfigCadastro config, String label) {
        String dataType = config.getType() != null ? config.getType().toUpperCase() : "STRING";
        boolean editable = config.isEditable();
        Integer width = config.getWidth();
        Integer scale = config.getScale();

        switch (dataType) {
            case "NUMBER":
            case "SHORT":
            case "INTEGER":
                NumberField numberField = new NumberField(label);
                numberField.setReadOnly(!editable);
                if (width != null) {
                    numberField.setWidth(width + "px");
                }
                return numberField;
            case "DECIMAL":
            case "FLOAT":
                NumberField decimalField = new NumberField(label);
                decimalField.setReadOnly(!editable);
                if (scale != null) {
                    decimalField.setStep(Math.pow(10, -scale));
                }
                if (width != null) {
                    decimalField.setWidth(width + "px");
                }
                return decimalField;
            case "STRING":
            default:
                TextField textField = new TextField(label);
                textField.setReadOnly(!editable);
                if (width != null) {
                    textField.setWidth(width + "px");
                }
                return textField;
        }
    }

    protected abstract List<String> getCamposFixos();

    @SuppressWarnings("unchecked")
    private <V> void addFieldWithBinding(Component field, String fieldName, String propertyPath, String dataType, boolean required) {
        String usuarioId = getUsuarioId();
        if (usuarioId == null) {
            usuarioId = "default";
        }
        GridColumnConfigCadastro config = columnConfigService.getColumnConfig(getClassName(), fieldName);
        boolean editable = config != null ? config.isEditable() : true;

        if (field instanceof TextField) {
            TextField textField = (TextField) field;
            textField.setReadOnly(!editable);
        } else if (field instanceof NumberField) {
            NumberField numberField = (NumberField) field;
            numberField.setReadOnly(!editable);
        }

        logger.debug("Configurando campo: fieldName={}, propertyPath={}, dataType={}, required={}", fieldName, propertyPath, dataType, required);

        Class<?> propertyType = getFieldType(propertyPath);
        if (propertyType == null) {
            logger.warn("Não foi possível determinar o tipo do campo '{}'. Usando tipo da view: {}.", propertyPath, dataType);
        } else {
            logger.debug("Tipo real da propriedade {}: {}", propertyPath, propertyType.getName());
        }

        switch (dataType.toUpperCase()) {
            case "NUMBER":
            case "SHORT":
            case "INTEGER":
                if (field instanceof NumberField) {
                    NumberField numberField = (NumberField) field;
                    Binder.BindingBuilder<T, Double> bindingBuilder = binder.forField(numberField);

                    if (required) {
                        bindingBuilder.asRequired(field.getId().orElse(fieldName) + " é obrigatório");
                    }

                    if (propertyType != null && String.class.isAssignableFrom(propertyType)) {
                        bindingBuilder
                            .withConverter(new Converter<Double, String>() {
                                @Override
                                public Result<String> convertToModel(Double value, ValueContext context) {
                                    if (value == null) {
                                        return Result.ok(null);
                                    }
                                    try {
                                        return Result.ok(String.valueOf(value.intValue()));
                                    } catch (Exception e) {
                                        return Result.error("O valor deve ser um número válido");
                                    }
                                }

                                @Override
                                public Double convertToPresentation(String value, ValueContext context) {
                                    if (value == null || value.trim().isEmpty()) {
                                        return null;
                                    }
                                    try {
                                        return Double.parseDouble(value);
                                    } catch (NumberFormatException e) {
                                        logger.warn("Falha ao converter String para Double no campo '{}': {}", fieldName, value);
                                        return null;
                                    }
                                }
                            })
                            .bind(propertyPath);
                    } else if (isShortField(propertyPath)) {
                        bindingBuilder
                            .withConverter(new Converter<Double, Short>() {
                                @Override
                                public Result<Short> convertToModel(Double value, ValueContext context) {
                                    if (value == null) {
                                        return Result.ok(null);
                                    }
                                    try {
                                        return Result.ok(value.shortValue());
                                    } catch (Exception e) {
                                        return Result.error("O valor deve ser um número curto válido");
                                    }
                                }

                                @Override
                                public Double convertToPresentation(Short value, ValueContext context) {
                                    return value != null ? value.doubleValue() : null;
                                }
                            })
                            .bind(propertyPath);
                    } else {
                        bindingBuilder
                            .withConverter(new Converter<Double, Integer>() {
                                @Override
                                public Result<Integer> convertToModel(Double value, ValueContext context) {
                                    if (value == null) {
                                        return Result.ok(null);
                                    }
                                    try {
                                        return Result.ok(value.intValue());
                                    } catch (Exception e) {
                                        return Result.error("O valor deve ser um número inteiro válido");
                                    }
                                }

                                @Override
                                public Double convertToPresentation(Integer value, ValueContext context) {
                                    return value != null ? value.doubleValue() : null;
                                }
                            })
                            .bind(propertyPath);
                    }
                }
                break;
            case "DECIMAL":
                if (field instanceof NumberField) {
                    NumberField decimalField = (NumberField) field;
                    Binder.BindingBuilder<T, Double> decimalBindingBuilder = binder.forField(decimalField);
                    if (required) {
                        decimalBindingBuilder.asRequired(field.getId().orElse(fieldName) + " é obrigatório");
                    }
                    decimalBindingBuilder
                        .withConverter(new Converter<Double, BigDecimal>() {
                            @Override
                            public Result<BigDecimal> convertToModel(Double value, ValueContext context) {
                                if (value == null) {
                                    return Result.ok(null);
                                }
                                try {
                                    return Result.ok(BigDecimal.valueOf(value));
                                } catch (Exception e) {
                                    return Result.error("O valor deve ser um número decimal válido");
                                }
                            }

                            @Override
                            public Double convertToPresentation(BigDecimal value, ValueContext context) {
                                return value != null ? value.doubleValue() : null;
                            }
                        })
                        .bind(propertyPath);
                }
                break;
            case "FLOAT":
                if (field instanceof NumberField) {
                    NumberField floatField = (NumberField) field;
                    Binder.BindingBuilder<T, Double> floatBindingBuilder = binder.forField(floatField);
                    if (required) {
                        floatBindingBuilder.asRequired(field.getId().orElse(fieldName) + " é obrigatório");
                    }
                    floatBindingBuilder
                        .withConverter(new Converter<Double, Float>() {
                            @Override
                            public Result<Float> convertToModel(Double value, ValueContext context) {
                                if (value == null) {
                                    return Result.ok(null);
                                }
                                try {
                                    return Result.ok(value.floatValue());
                                } catch (Exception e) {
                                    return Result.error("O valor deve ser um número decimal válido");
                                }
                            }

                            @Override
                            public Double convertToPresentation(Float value, ValueContext context) {
                                return value != null ? value.doubleValue() : null;
                            }
                        })
                        .bind(propertyPath);
                }
                break;
            case "DATE":
                Class<?> fieldType = getFieldType(propertyPath);
                if (fieldType == null) {
                    logger.warn("Não foi possível determinar o tipo do campo '{}'. Assumindo java.util.Date.", propertyPath);
                    fieldType = java.util.Date.class;
                }
                logger.debug("Tipo real do campo {}: {}", propertyPath, fieldType.getName());
                if (java.util.Date.class.isAssignableFrom(fieldType) || java.sql.Date.class.isAssignableFrom(fieldType)) {
                    Binder.BindingBuilder<T, Date> dateBinding = binder.forField((TextField) field)
                        .withConverter(new StringToDateConverter("A data deve estar no formato dd/MM/yyyy"));
                    if (required) {
                        dateBinding.asRequired(field.getId().orElse(fieldName) + " é obrigatório");
                    }
                    dateBinding.bind(propertyPath);
                } else if (LocalDateTime.class.isAssignableFrom(fieldType)) {
                    Binder.BindingBuilder<T, LocalDateTime> dateTimeBinding = binder.forField((TextField) field)
                        .withConverter(new StringToLocalDateTimeConverter("A data deve estar no formato dd/MM/yyyy HH:mm:ss"));
                    if (required) {
                        dateTimeBinding.asRequired(field.getId().orElse(fieldName) + " é obrigatório");
                    }
                    dateTimeBinding.bind(propertyPath);
                } else {
                    logger.error("Campo '{}' tem tipo incompatível para DATE: {}. Usando String como fallback.", propertyPath, fieldType.getName());
                    Binder.BindingBuilder<T, String> stringBinding = binder.forField((TextField) field);
                    if (required) {
                        stringBinding.asRequired(field.getId().orElse(fieldName) + " é obrigatório");
                        stringBinding.withValidator(Validator.from(value -> value != null && !value.trim().isEmpty(), field.getId().orElse(fieldName) + " não pode ser vazio"));
                    }
                    stringBinding.bind(propertyPath);
                }
                break;
            case "STRING":
            default:
                if (field instanceof TextField) {
                    TextField textField = (TextField) field;
                    Binder.BindingBuilder<T, String> stringBinding = binder.forField(textField);

                    if (propertyType != null) {
                        if (Integer.class.isAssignableFrom(propertyType) || int.class.isAssignableFrom(propertyType)) {
                            stringBinding.withConverter(new StringToIntegerConverter("O valor deve ser um número inteiro válido"));
                        } else if (Long.class.isAssignableFrom(propertyType) || long.class.isAssignableFrom(propertyType)) {
                            stringBinding.withConverter(new Converter<String, Long>() {
                                @Override
                                public Result<Long> convertToModel(String value, ValueContext context) {
                                    if (value == null || value.trim().isEmpty()) {
                                        return Result.ok(null);
                                    }
                                    try {
                                        return Result.ok(Long.parseLong(value));
                                    } catch (NumberFormatException e) {
                                        return Result.error("O valor deve ser um número longo válido");
                                    }
                                }

                                @Override
                                public String convertToPresentation(Long value, ValueContext context) {
                                    return value != null ? value.toString() : null;
                                }
                            });
                        } else if (Short.class.isAssignableFrom(propertyType) || short.class.isAssignableFrom(propertyType)) {
                            stringBinding.withConverter(new StringToShortConverter("O valor deve ser um número curto válido"));
                        }
                    }

                    if (required) {
                        stringBinding.asRequired(field.getId().orElse(fieldName) + " é obrigatório");
                        stringBinding.withValidator(Validator.from(value -> value != null && !value.trim().isEmpty(), field.getId().orElse(fieldName) + " não pode ser vazio"));
                    }
                    stringBinding.bind(propertyPath);
                }
                break;
        }

        FieldConfig<T> configField = new FieldConfig<>(field.getId().orElse(fieldName), field, required, null, propertyPath);
        fields.add(configField);
        logger.info("Adicionando campo '{}': fieldName={}, dataType={}, required={}", field.getId().orElse(fieldName), fieldName, dataType, required);
    }

    @SuppressWarnings("unchecked")
    private <V> void addComboBoxFieldWithBinding(ComboBox<V> comboBox, String fieldName, String propertyPath, boolean required, String entityName, Map<String, String> dropdownValueMap) {
        String usuarioId = getUsuarioId();
        if (usuarioId == null) {
            usuarioId = "default";
        }
        GridColumnConfigCadastro config = columnConfigService.getColumnConfig(getClassName(), fieldName);
        boolean visible = config != null ? config.isVisible() : true;
        boolean editable = config != null ? config.isEditable() : true;

        if (!visible) {
            logger.info("Campo '{}' não adicionado ao formulário porque não está visível", fieldName);
            return;
        }

        comboBox.setReadOnly(!editable);

        logger.debug("Configurando ComboBox: fieldName={}, propertyPath={}, entityName={}, required={}, dropdownValueMap={}", fieldName, propertyPath, entityName, required, dropdownValueMap);

         if (dropdownValueMap != null && !dropdownValueMap.isEmpty()) {
            Converter<String, String> valueConverter = new Converter<String, String>() {
                @Override
                public Result<String> convertToModel(String displayValue, ValueContext context) {
                    if (displayValue == null) {
                        return Result.ok(null);
                    }
                    for (Map.Entry<String, String> entry : dropdownValueMap.entrySet()) {
                        if (entry.getValue().equals(displayValue)) {
                            return Result.ok(entry.getKey());
                        }
                    }
                    return Result.error("Valor inválido: " + displayValue);
                }

                @Override
                public String convertToPresentation(String rawValue, ValueContext context) {
                    if (rawValue == null) {
                        return null;
                    }
                    String displayValue = dropdownValueMap.get(rawValue);
                    if (displayValue == null) {
                        logger.warn("Valor bruto '{}' não encontrado no mapeamento para o campo '{}'. Usando valor bruto como fallback.", rawValue, fieldName);
                        return rawValue;
                    }
                    return displayValue;
                }
            };

            try {
                String[] parts = propertyPath.split("\\.");
                Object currentObject = entity;
                for (int i = 0; i < parts.length - 1; i++) {
                    java.lang.reflect.Field nestedField = currentObject.getClass().getDeclaredField(parts[i]);
                    nestedField.setAccessible(true);
                    currentObject = nestedField.get(currentObject);
                    if (currentObject == null) {
                        break;
                    }
                }
                if (currentObject != null) {
                    java.lang.reflect.Field field = currentObject.getClass().getDeclaredField(parts[parts.length - 1]);
                    field.setAccessible(true);
                    Object rawValue = field.get(currentObject);
                    if (rawValue != null) {
                        String displayValue = valueConverter.convertToPresentation(rawValue.toString(), new ValueContext());
                        comboBox.setValue((V) displayValue);
                        logger.debug("Valor inicial do campo {} convertido de '{}' para '{}'", fieldName, rawValue, displayValue);
                    }
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                logger.error("Erro ao acessar o valor inicial do campo {} na entidade: {}", propertyPath, e.getMessage(), e);
            }

            Binder.BindingBuilder<T, String> binding = binder.forField((ComboBox<String>) comboBox)
                    .withConverter(valueConverter);
            if (required) {
                binding.asRequired(comboBox.getLabel() + " é obrigatório");
            }
            binding.bind(propertyPath);
        } else {
            logger.warn("Nenhum mapeamento disponível para o campo '{}'. Usando valores brutos diretamente.", fieldName);
            Binder.BindingBuilder<T, String> binding = binder.forField((ComboBox<String>) comboBox);
            if (required) {
                binding.asRequired(comboBox.getLabel() + " é obrigatório");
            }
            binding.bind(propertyPath);
        }

        FieldConfig<T> configField = new FieldConfig<>(comboBox.getLabel(), comboBox, required, null, propertyPath);
        fields.add(configField);
        logger.info("Adicionando ComboBox '{}': fieldName={}, entityName={}, required={}", comboBox.getLabel(), fieldName, entityName, required);
    }

    private boolean isShortField(String propertyPath) {
        try {
            String[] parts = propertyPath.split("\\.");
            Class<?> currentClass = entity.getClass();
            java.lang.reflect.Field field = null;

            for (int i = 0; i < parts.length; i++) {
                field = currentClass.getDeclaredField(parts[i]);
                field.setAccessible(true);
                if (i < parts.length - 1) {
                    currentClass = field.getType();
                }
            }

            boolean isShort = field.getType() == Short.class || field.getType() == short.class;
            logger.debug("Verificando tipo de '{}': é Short? {}", propertyPath, isShort);
            return isShort;
        } catch (NoSuchFieldException e) {
            logger.warn("Campo '{}' não encontrado na entidade {}. Assumindo que não é Short.", propertyPath, entity.getClass().getSimpleName());
            return false;
        }
    }

    private boolean isFloatField(String propertyPath) {
        try {
            String[] parts = propertyPath.split("\\.");
            Class<?> currentClass = entity.getClass();
            java.lang.reflect.Field field = null;

            for (int i = 0; i < parts.length; i++) {
                field = currentClass.getDeclaredField(parts[i]);
                field.setAccessible(true);
                if (i < parts.length - 1) {
                    currentClass = field.getType();
                }
            }

            boolean isFloat = field.getType() == Float.class || field.getType() == float.class;
            logger.debug("Verificando tipo de '{}': é Float? {}", propertyPath, isFloat);
            return isFloat;
        } catch (NoSuchFieldException e) {
            logger.warn("Campo '{}' não encontrado na entidade {}. Assumindo que não é Float.", propertyPath, entity.getClass().getSimpleName());
            return false;
        }
    }

    private Class<?> getFieldType(String propertyPath) {
        try {
            String[] parts = propertyPath.split("\\.");
            Class<?> currentClass = entity.getClass();
            java.lang.reflect.Field field = null;

            for (int i = 0; i < parts.length; i++) {
                field = currentClass.getDeclaredField(parts[i]);
                field.setAccessible(true);
                if (i < parts.length - 1) {
                    currentClass = field.getType();
                }
            }

            return field.getType();
        } catch (NoSuchFieldException e) {
            logger.warn("Campo '{}' não encontrado na entidade {}.", propertyPath, entity.getClass().getSimpleName());
            return null;
        }
    }

    private String toCamelCase(String fieldName) {
        StringBuilder camelCase = new StringBuilder();
        boolean capitalizeNext = false;
        for (char c : fieldName.toCharArray()) {
            if (c == '_') {
                capitalizeNext = true;
            } else {
                camelCase.append(capitalizeNext ? Character.toUpperCase(c) : c);
                capitalizeNext = false;
            }
        }
        return camelCase.toString();
    }

    protected void buildForm() {
        FormLayout form = new FormLayout();

        for (FieldConfig<T> fieldConfig : fields) {
            form.add(fieldConfig.getField());
        }

        logger.debug("Vinculando entidade ao formulário: {}", entity);
        binder.setBean(entity);

        Button saveButton = new Button("Salvar", event -> {
            if (binder.validate().isOk()) {
                try {
                    beforeSave(entity);
                    service.save(entity);
                    Notification.show(getSuccessMessage(), 3000, Notification.Position.TOP_CENTER);
                    logger.info("Entidade salva: {}", entity);
                    if (onSaveCallback != null) {
                        onSaveCallback.accept(entity);
                    }
                    dialog.close();
                } catch (Exception e) {
                    logger.error("Erro ao salvar entidade: {}", e.getMessage(), e);
                    Notification.show("Erro ao salvar: " + e.getMessage(), 3000, Notification.Position.TOP_CENTER);
                }
            }
        });

        Button cancelButton = new Button("Cancelar", event -> dialog.close());

        form.add(saveButton, cancelButton);
        dialog.add(form);
    }

    public void open() {
        logger.info("Abrindo diálogo de cadastro");
        dialog.open();
    }

    protected abstract String getSuccessMessage();

    protected abstract void beforeSave(T entity);

    protected static class FieldConfig<T> {
        private final String label;
        private final Component field;
        private final boolean required;
        private final Consumer<Binder.BindingBuilder> validator;
        private final String propertyName;

        public FieldConfig(String label, Component field, boolean required,
                           Consumer<Binder.BindingBuilder> validator, String propertyName) {
            this.label = label;
            this.field = field;
            this.required = required;
            this.validator = validator;
            this.propertyName = propertyName;
        }

        public Component getField() {
            return field;
        }

        public boolean isRequired() {
            return required;
        }

        public Consumer<Binder.BindingBuilder> getValidator() {
            return validator;
        }

        public String getPropertyName() {
            return propertyName;
        }
    }

    public static class StringToDateConverter implements Converter<String, Date> {
        private final String errorMessage;

        public StringToDateConverter(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        @Override
        public Result<Date> convertToModel(String value, ValueContext context) {
            if (value == null || value.trim().isEmpty()) {
                return Result.ok(null);
            }
            try {
                return Result.ok(new Date());
            } catch (Exception e) {
                return Result.error(errorMessage);
            }
        }

        @Override
        public String convertToPresentation(Date value, ValueContext context) {
            if (value == null) {
                return null;
            }
            return value.toString();
        }
    }

    public static class StringToLocalDateTimeConverter implements Converter<String, LocalDateTime> {
        private final String errorMessage;

        public StringToLocalDateTimeConverter(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        @Override
        public Result<LocalDateTime> convertToModel(String value, ValueContext context) {
            if (value == null || value.trim().isEmpty()) {
                return Result.ok(null);
            }
            try {
                return Result.ok(LocalDateTime.now());
            } catch (Exception e) {
                return Result.error(errorMessage);
            }
        }

        @Override
        public String convertToPresentation(LocalDateTime value, ValueContext context) {
            if (value == null) {
                return null;
            }
            return value.toString();
        }
    }

    protected String getUsuarioId() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || !authentication.isAuthenticated()) {
                logger.warn("Autenticação não encontrada ou usuário não autenticado.");
                return null;
            }

            Object principal = authentication.getPrincipal();
            logger.debug("Principal obtido do SecurityContextHolder: {}", principal);

            if (principal instanceof UserDetails) {
                String username = ((UserDetails) principal).getUsername();
                logger.debug("Usuário logado: {}", username);
                return username;
            } else {
                logger.warn("Principal não é UserDetails: {}", principal);
                return null;
            }
        } catch (Exception e) {
            logger.error("Erro ao obter usuário logado: {}", e.getMessage(), e);
            return null;
        }
    }
}