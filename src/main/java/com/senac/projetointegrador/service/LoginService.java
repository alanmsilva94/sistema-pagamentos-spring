package com.senac.projetointegrador.service;

import com.senac.projetointegrador.data.*;
import com.senac.projetointegrador.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LoginService {
    
    @Autowired
    private LoginRepository loginRepository;
    
    // Método simples para hash básico (em produção use BCrypt)
    private String simpleHash(String text) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(text.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : array) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            return text;
        }
    }
    
    public boolean autenticar(String usuario, String senha) {
        String senhaHash = simpleHash(senha);
        return loginRepository.findByUsuarioAndSenha(usuario, senhaHash).isPresent();
    }
    
    public LoginEntity save(LoginEntity login) {
        // Criptografa a senha antes de salvar
        login.setSenha(simpleHash(login.getSenha()));
        return loginRepository.save(login);
    }
    
    public Optional<LoginEntity> findByUsuario(String usuario) {
        return loginRepository.findByUsuario(usuario);
    }
}