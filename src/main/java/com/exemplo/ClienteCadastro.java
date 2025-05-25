package com.exemplo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ClienteCadastro extends GenericaCadastro<Cliente> {

    @Autowired
    public ClienteCadastro(ClienteRepository clienteRepository) {
        super(clienteRepository, new Cliente(), null);
    }

    @Override
    protected String getTitle() {
        return entity != null && entity.getId() != null ? "Editar Cliente" : "Novo Cliente";
    }

    @Override
    protected List<String> getCamposFixos() {
        return Arrays.asList("cd_cliente", "nm_cliente", "tipo", "endereco", "bairro", "fone", "celular", "cpf");
    }

    @Override
    protected String getClassName() {
        return "cliente";
    }

    @Override
    protected String getSuccessMessage() {
        return "Cliente salvo com sucesso!";
    }

    @Override
    protected void beforeSave(Cliente cliente) {
        if (cliente.getId() == null) {
            cliente.setId(new ClienteId());
        }
        if (cliente.getId().getCdEmpresa() == null) {
            cliente.getId().setCdEmpresa(1L); // Substitua pela lógica real
        }
    }
}