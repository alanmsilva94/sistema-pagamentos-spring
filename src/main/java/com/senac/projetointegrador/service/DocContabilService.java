package com.senac.projetointegrador.service;

import com.senac.projetointegrador.data.*;
import com.senac.projetointegrador.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class DocContabilService {
    
    @Autowired
    private DocContabilRepository docContabilRepository;
    
    public List<DocContabilEntity> findAll() {
        return docContabilRepository.findAll();
    }
    
    public Optional<DocContabilEntity> findById(Integer id) {
        return docContabilRepository.findById(id);
    }
    
    public DocContabilEntity save(DocContabilEntity documento) {
        return docContabilRepository.save(documento);
    }
    
    public void deleteById(Integer id) {
        docContabilRepository.deleteById(id);
    }
}