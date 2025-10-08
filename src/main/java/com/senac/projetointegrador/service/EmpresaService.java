package com.senac.projetointegrador.service;

import com.senac.projetointegrador.data.*;
import com.senac.projetointegrador.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class EmpresaService {
    
    @Autowired
    private EmpresaRepository empresaRepository;
    
    public List<EmpresaEntity> findAll() {
        return empresaRepository.findAll();
    }
    
    public Optional<EmpresaEntity> findById(Integer id) {
        return empresaRepository.findById(id);
    }
    
    public EmpresaEntity save(EmpresaEntity empresa) {
        return empresaRepository.save(empresa);
    }
    
    public void deleteById(Integer id) {
        empresaRepository.deleteById(id);
    }
    
    public Optional<EmpresaEntity> findByCnpj(String cnpj) {
        return empresaRepository.findByCnpj(cnpj);
    }
}