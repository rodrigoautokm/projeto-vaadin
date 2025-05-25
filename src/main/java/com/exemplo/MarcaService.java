package com.exemplo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarcaService {

    private final MarcaRepository repository;

    @Autowired
    public MarcaService(MarcaRepository repository) {
        this.repository = repository;
    }

    public List<Marca> listar() {
        return repository.findAll();
    }

    public Marca findById(Short cdMarca) { // Alterado de Integer para Short
        return repository.findById(cdMarca).orElse(null);
    }

    public Marca save(Marca marca) {
        return repository.save(marca);
    }
}