package com.senac.projetointegrador.controller;

import com.senac.projetointegrador.service.LoginService;
import com.senac.projetointegrador.data.LoginEntity;  // IMPORT ADICIONADO
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;  // IMPORT ADICIONADO

@Controller
public class LoginController {
    
    @Autowired
    private LoginService loginService;
    
    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }
    
    @GetMapping("/login")
    public String showLoginPage(@RequestParam(value = "error", required = false) String error, 
                               Model model) {
        if (error != null) {
            model.addAttribute("error", "Usuário ou senha inválidos");
        }
        return "login";
    }
    
    @PostMapping("/login")
    public String login(@RequestParam String username, 
                       @RequestParam String password, 
                       Model model) {
        if (loginService.autenticar(username, password)) {
            return "redirect:/inicio";
        } else {
            return "redirect:/login?error=true";
        }
    }
    
    @GetMapping("/recuperar-senha")
    public String recuperarSenhaPage(@RequestParam(value = "success", required = false) String success,
                                    @RequestParam(value = "error", required = false) String error,
                                    Model model) {
        if (success != null) {
            model.addAttribute("success", "Senha alterada com sucesso!");
        }
        if (error != null) {
            model.addAttribute("error", "Erro ao alterar senha. Verifique o usuário.");
        }
        return "recuperar";
    }
    
    @GetMapping("/inicio")
    public String inicioPage() {
        return "inicio";
    }
    
    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }
    
    @PostMapping("/recuperar-senha")
    public String recuperarSenha(@RequestParam String login,
                                @RequestParam String newPassword,
                                @RequestParam String confirmPassword,
                                Model model) {
        
        // Validações
        if (!newPassword.equals(confirmPassword)) {
            return "redirect:/recuperar-senha?error=senhasNaoConferem";
        }
        
        if (newPassword.length() < 3) {
            return "redirect:/recuperar-senha?error=senhaFraca";
        }
        
        // Buscar usuário
        Optional<LoginEntity> usuarioOpt = loginService.findByUsuario(login);
        if (usuarioOpt.isEmpty()) {
            return "redirect:/recuperar-senha?error=usuarioNaoEncontrado";
        }
        
        // Atualizar senha
        LoginEntity usuario = usuarioOpt.get();
        usuario.setSenha(newPassword); // Será criptografada pelo service
        loginService.save(usuario);
        
        return "redirect:/recuperar-senha?success=true";
    }
}