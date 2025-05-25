package com.exemplo.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpresaService {
    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private PasswordEncoderService passwordEncoder;

    public void cadastrarEmpresa(Empresa empresa) {
        String senhaCriptografada = passwordEncoder.encode(empresa.getSenhaBd());
        empresa.setSenhaBd(senhaCriptografada);
        empresaRepository.save(empresa);
    }
}