package com.senac.projetointegrador.repository;


import com.senac.projetointegrador.data.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


@Repository
public interface LoginRepository extends JpaRepository<LoginEntity, Integer> {
    Optional<LoginEntity> findByUsuarioAndSenha(String usuario, String senha);
    Optional<LoginEntity> findByUsuario(String usuario);
 
}