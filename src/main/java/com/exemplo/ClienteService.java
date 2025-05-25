package com.exemplo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    public void salvarCliente(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    public Optional<Cliente> buscar(ClienteId id) {
        return clienteRepository.findById(id);
    }

    public void excluir(Cliente cliente) {
        clienteRepository.delete(cliente);
    }
}
