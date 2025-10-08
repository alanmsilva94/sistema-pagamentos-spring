package com.senac.projetointegrador.service;

import com.senac.projetointegrador.data.BancoEntity;
import com.senac.projetointegrador.data.EmpresaEntity;
import com.senac.projetointegrador.repository.BancoRepository;
import com.senac.projetointegrador.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BancoService {
    
    @Autowired
    private BancoRepository bancoRepository;
    
    @Autowired
    private EmpresaRepository empresaRepository;
    
    public List<BancoEntity> findAll() {
        return bancoRepository.findAll();
    }
    
    public Optional<BancoEntity> findById(Integer id) {
        return bancoRepository.findById(id);
    }
    
    public BancoEntity save(BancoEntity banco) {
        if (banco.getEmpresa() != null) {
            if (banco.getEmpresa().getId() != null) {
                Optional<EmpresaEntity> empresaExistente = empresaRepository.findById(banco.getEmpresa().getId());
                if (empresaExistente.isPresent()) {
                    banco.setEmpresa(empresaExistente.get());
                }
            } else {
                EmpresaEntity empresaSalva = empresaRepository.save(banco.getEmpresa());
                banco.setEmpresa(empresaSalva);
            }
        }
        return bancoRepository.save(banco);
    }
    
    public void deleteById(Integer id) {
        bancoRepository.deleteById(id);
    }
    
    public List<BancoEntity> findByEmpresaId(Integer empresaId) {
        return bancoRepository.findByEmpresaId(empresaId);
    }
}