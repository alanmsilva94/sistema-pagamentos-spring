package com.senac.projetointegrador.service;


import com.senac.projetointegrador.data.*;
import com.senac.projetointegrador.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DepartamentoService {
    
    @Autowired
    private DepartamentoRepository departamentoRepository;
    
    public List<DepartamentoEntity> findAll() {
        return departamentoRepository.findAll();
    }
    
    public Optional<DepartamentoEntity> findById(Integer id) {
        return departamentoRepository.findById(id);
    }
    
    public DepartamentoEntity save(DepartamentoEntity departamento) {
        return departamentoRepository.save(departamento);
    }
    
    public void deleteById(Integer id) {
        departamentoRepository.deleteById(id);
    }
}