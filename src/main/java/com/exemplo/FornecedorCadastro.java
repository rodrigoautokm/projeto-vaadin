package com.exemplo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class FornecedorCadastro extends GenericaCadastro<Fornecedor> {

    private final FornecedorRepository repository;

    @Autowired
    public FornecedorCadastro(FornecedorRepository repository) {
        super(repository, new Fornecedor(), savedFornecedor -> {});
        this.repository = repository;
    }

    @Override
    protected String getTitle() {
        return "Cadastro de Fornecedor";
    }

    @Override
    protected String getClassName() {
        return "fornecedor";
    }

    @Override
    protected List<String> getCamposFixos() {
        return Arrays.asList("nmFornecedor", "tipo");
    }

    @Override
    protected String getSuccessMessage() {
        return "Fornecedor salvo com sucesso!";
    }

    @Override
    protected void beforeSave(Fornecedor entity) {
        // Nenhuma lógica específica necessária aqui, já que os valores serão preenchidos pelo binder
    }
}