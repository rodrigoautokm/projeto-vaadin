package com.exemplo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ProdutoCadastro extends GenericaCadastro<Produto> {

    @Autowired
    public ProdutoCadastro(ProdutoRepository produtoRepository) {
        super(produtoRepository, new Produto(), null);
    }

    @Override
    protected String getTitle() {
        return entity != null ? "Editar Produto" : "Novo Produto";
    }

    @Override
    protected List<String> getCamposFixos() {
        return Arrays.asList(
            "cd_produto", "nr_digito", "ds_produto", "ds_abreviacao", "cd_subgrupo", 
            "cd_grupo", "cd_marca", "cd_cor", "voltagem", "cd_fornecedor", "situacao", "vl_venda"
        );
    }

    @Override
    protected String getClassName() {
        return "produto";
    }

    @Override
    protected String getSuccessMessage() {
        return "Produto salvo com sucesso!";
    }

    @Override
    protected void beforeSave(Produto produto) {
        // Caso precise preencher algo automaticamente antes de salvar
    }
}