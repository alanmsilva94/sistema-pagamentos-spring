package com.senac.projetointegrador.controller;


import com.senac.projetointegrador.data.*;
import com.senac.projetointegrador.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/bancos")
public class BancoController {
    
    @Autowired
    private BancoService bancoService;
    
    @Autowired
    private EmpresaService empresaService;
    
    @GetMapping
    public String listBancos(Model model) {
        List<BancoEntity> bancos = bancoService.findAll();
        model.addAttribute("bancos", bancos);
        return "bancos";
    }
    
    @GetMapping("/novo")
    public String showBancoForm(Model model) {
        model.addAttribute("banco", new BancoEntity());
        model.addAttribute("empresas", empresaService.findAll());
        return "banco-form";
    }
    
    @PostMapping("/salvar")
    public String saveBanco(@ModelAttribute BancoEntity banco) {
        bancoService.save(banco);
        return "redirect:/bancos";
    }
}