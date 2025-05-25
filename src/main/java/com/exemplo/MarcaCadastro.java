package com.exemplo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class MarcaCadastro extends GenericaCadastro<Marca> {

    private final MarcaRepository repository;

    @Autowired
    public MarcaCadastro(MarcaRepository repository) {
        super(repository, new Marca(), savedMarca -> {});
        this.repository = repository;
    }

    @Override
    protected String getTitle() {
        return "Cadastro de Marca";
    }

    @Override
    protected String getClassName() {
        return "marca";
    }

    @Override
    protected List<String> getCamposFixos() {
        return Arrays.asList("dsMarca", "situacao", "comissao");
    }

    @Override
    protected String getSuccessMessage() {
        return "Marca salva com sucesso!";
    }

    @Override
    protected void beforeSave(Marca entity) {
        // Nenhuma lógica específica necessária aqui, já que os valores serão preenchidos pelo binder
    }
}