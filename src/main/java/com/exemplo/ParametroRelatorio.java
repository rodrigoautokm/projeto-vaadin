package com.exemplo;

public class ParametroRelatorio {

    private String field;
    private String tipo;
    private String header;
    private String valorDefault;
    private String dropdownValues;
    private boolean obrigatorio;

    // ✅ Construtor padrão necessário para frameworks como JdbcTemplate
    public ParametroRelatorio() {
    }

    // ✅ Construtor com os principais campos usados pelo JdbcTemplate
    public ParametroRelatorio(String field, String tipo, String dropdownValues, boolean obrigatorio) {
        this.field = field;
        this.tipo = tipo;
        this.dropdownValues = dropdownValues;
        this.obrigatorio = obrigatorio;
    }

    // ✅ Construtor adicional, caso use em algum lugar
    public ParametroRelatorio(String field, String tipo, String header, String dropdownValues, boolean obrigatorio) {
        this.field = field;
        this.tipo = tipo;
        this.header = header;
        this.dropdownValues = dropdownValues;
        this.obrigatorio = obrigatorio;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getValorDefault() {
        return valorDefault;
    }

    public void setValorDefault(String valorDefault) {
        this.valorDefault = valorDefault;
    }

    public String getDropdownValues() {
        return dropdownValues;
    }

    public void setDropdownValues(String dropdownValues) {
        this.dropdownValues = dropdownValues;
    }

    public boolean isObrigatorio() {
        return obrigatorio;
    }

    public void setObrigatorio(boolean obrigatorio) {
        this.obrigatorio = obrigatorio;
    }
}
