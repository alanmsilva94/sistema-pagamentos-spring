package com.senac.projetointegrador.repository;

import com.senac.projetointegrador.data.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface BancoRepository extends JpaRepository<BancoEntity, Integer> {
    List<BancoEntity> findByEmpresaId(Integer empresaId);
}