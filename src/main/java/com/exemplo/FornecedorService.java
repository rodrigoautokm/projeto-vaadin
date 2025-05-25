package com.exemplo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    public List<Fornecedor> listar() {
        return fornecedorRepository.findAll();
    }

    public void salvarFornecedor(Fornecedor fornecedor) {
        fornecedorRepository.save(fornecedor);
    }

    public Optional<Fornecedor> buscar(Integer id) {
        return fornecedorRepository.findById(id);
    }

    public void excluir(Fornecedor fornecedor) {
        fornecedorRepository.delete(fornecedor);
    }
}
