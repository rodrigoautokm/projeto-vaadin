package com.exemplo;

import java.util.Map;

public class RelatorioDinamicoResult {

    private final Map<String, Object> valores;

    public RelatorioDinamicoResult(Map<String, Object> valores) {
        this.valores = valores;
    }

    public Map<String, Object> getValores() {
        return valores;
    }
}
