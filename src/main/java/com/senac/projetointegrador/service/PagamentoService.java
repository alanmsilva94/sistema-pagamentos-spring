package com.senac.projetointegrador.service;

import com.senac.projetointegrador.data.PagamentoEntity;
import com.senac.projetointegrador.data.EmpresaEntity;
import com.senac.projetointegrador.data.FuncionarioEntity;
import com.senac.projetointegrador.repository.PagamentoRepository;
import com.senac.projetointegrador.repository.EmpresaRepository;
import com.senac.projetointegrador.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PagamentoService {
    
    @Autowired
    private PagamentoRepository pagamentoRepository;
    
    @Autowired
    private EmpresaRepository empresaRepository;
    
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    
    public List<PagamentoEntity> findAll() {
        return pagamentoRepository.findAll();
    }
    
    public Optional<PagamentoEntity> findById(Integer id) {
        return pagamentoRepository.findById(id);
    }
    
    public PagamentoEntity save(PagamentoEntity pagamento) {
        if (pagamento.getEmpresa() != null && pagamento.getEmpresa().getId() != null) {
            Optional<EmpresaEntity> empresaExistente = empresaRepository.findById(pagamento.getEmpresa().getId());
            if (empresaExistente.isPresent()) {
                pagamento.setEmpresa(empresaExistente.get());
            }
        }
        
        if (pagamento.getFuncionario() != null && pagamento.getFuncionario().getId() != null) {
            Optional<FuncionarioEntity> funcionarioExistente = funcionarioRepository.findById(pagamento.getFuncionario().getId());
            if (funcionarioExistente.isPresent()) {
                pagamento.setFuncionario(funcionarioExistente.get());
            }
        }
        
        return pagamentoRepository.save(pagamento);
    }
    
    public void deleteById(Integer id) {
        pagamentoRepository.deleteById(id);
    }
    
    public List<PagamentoEntity> findByEmpresaId(Integer empresaId) {
        return pagamentoRepository.findByEmpresaId(empresaId);
    }
    
    public List<PagamentoEntity> findByFuncionarioId(Integer funcionarioId) {
        return pagamentoRepository.findByFuncionarioId(funcionarioId);
    }
}