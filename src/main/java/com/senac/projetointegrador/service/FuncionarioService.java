package com.senac.projetointegrador.service;

import com.senac.projetointegrador.data.FuncionarioEntity;
import com.senac.projetointegrador.data.DepartamentoEntity;
import com.senac.projetointegrador.repository.FuncionarioRepository;
import com.senac.projetointegrador.repository.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FuncionarioService {
    
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    
    @Autowired
    private DepartamentoRepository departamentoRepository;
    
    public List<FuncionarioEntity> findAll() {
        return funcionarioRepository.findAll();
    }
    
    public Optional<FuncionarioEntity> findById(Integer id) {
        return funcionarioRepository.findById(id);
    }
    
    public FuncionarioEntity save(FuncionarioEntity funcionario) {
        if (funcionario.getDepartamento() != null) {
            if (funcionario.getDepartamento().getId() != null) {
                Optional<DepartamentoEntity> departamentoExistente = departamentoRepository.findById(funcionario.getDepartamento().getId());
                if (departamentoExistente.isPresent()) {
                    funcionario.setDepartamento(departamentoExistente.get());
                }
            } else {
                DepartamentoEntity departamentoSalvo = departamentoRepository.save(funcionario.getDepartamento());
                funcionario.setDepartamento(departamentoSalvo);
            }
        }
        
        return funcionarioRepository.save(funcionario);
    }
    
    public void deleteById(Integer id) {
        funcionarioRepository.deleteById(id);
    }
    
    public List<FuncionarioEntity> findByDepartamentoId(Integer departamentoId) {
        return funcionarioRepository.findByDepartamentoId(departamentoId);
    }
}