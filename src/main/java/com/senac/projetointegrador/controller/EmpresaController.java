package com.senac.projetointegrador.controller;

import com.senac.projetointegrador.data.EmpresaEntity;
import com.senac.projetointegrador.data.BancoEntity;
import com.senac.projetointegrador.data.PagamentoEntity;
import com.senac.projetointegrador.service.EmpresaService;
import com.senac.projetointegrador.service.BancoService;
import com.senac.projetointegrador.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/empresas")
public class EmpresaController {
    
    @Autowired
    private EmpresaService empresaService;
    
    @Autowired
    private BancoService bancoService;
    
    @Autowired
    private PagamentoService pagamentoService;
    
    @GetMapping
    public String listEmpresas(Model model, @RequestParam(required = false) String error) {
        List<EmpresaEntity> empresas = empresaService.findAll();
        model.addAttribute("empresas", empresas);
        
        if ("delete".equals(error)) {
            model.addAttribute("errorMessage", "Não foi possível excluir a empresa. Existem registros vinculados a ela.");
        }
        
        return "empresas";
    }
    
    @GetMapping("/nova")
    public String showEmpresaForm(Model model) {
        model.addAttribute("empresa", new EmpresaEntity());
        model.addAttribute("banco", new BancoEntity());
        return "empresa-form";
    }
    
    @PostMapping("/salvar")
    public String saveEmpresa(@ModelAttribute EmpresaEntity empresa, 
                             @RequestParam(required = false) Integer codigoBanco,
                             @RequestParam(required = false) String nomeBanco,
                             @RequestParam(required = false) String tipoConta,
                             @RequestParam(required = false) Integer agencia,
                             @RequestParam(required = false) Integer digitoAgencia,
                             @RequestParam(required = false) Integer conta,
                             @RequestParam(required = false) Integer digitoConta,
                             @RequestParam(required = false) String chavePix,
                             @RequestParam(required = false) String nomeRecebedor,
                             @RequestParam(required = false) String cnpjRecebedor) {
        
        EmpresaEntity empresaSalva = empresaService.save(empresa);
        
        List<BancoEntity> bancosExistentes = bancoService.findByEmpresaId(empresaSalva.getId());
        BancoEntity bancoParaSalvar;
        
        if (!bancosExistentes.isEmpty()) {
            bancoParaSalvar = bancosExistentes.get(0);
        } else {
            bancoParaSalvar = new BancoEntity();
            bancoParaSalvar.setEmpresa(empresaSalva);
        }
        
        if (codigoBanco != null) bancoParaSalvar.setCodigoBanco(codigoBanco);
        if (nomeBanco != null && !nomeBanco.trim().isEmpty()) bancoParaSalvar.setNomeBanco(nomeBanco);
        if (tipoConta != null && !tipoConta.trim().isEmpty()) bancoParaSalvar.setTipoConta(tipoConta);
        if (agencia != null) bancoParaSalvar.setAgencia(agencia);
        if (digitoAgencia != null) bancoParaSalvar.setDigitoAgencia(digitoAgencia);
        if (conta != null) bancoParaSalvar.setConta(conta);
        if (digitoConta != null) bancoParaSalvar.setDigitoConta(digitoConta);
        if (chavePix != null && !chavePix.trim().isEmpty()) bancoParaSalvar.setChavePix(chavePix);
        if (nomeRecebedor != null && !nomeRecebedor.trim().isEmpty()) bancoParaSalvar.setNomeRecebedor(nomeRecebedor);
        if (cnpjRecebedor != null && !cnpjRecebedor.trim().isEmpty()) bancoParaSalvar.setCnpjRecebedor(cnpjRecebedor);
        
        if ((codigoBanco != null && nomeBanco != null && !nomeBanco.trim().isEmpty()) || 
            !bancosExistentes.isEmpty()) {
            bancoService.save(bancoParaSalvar);
        }
        
        return "redirect:/empresas";
    }
    
    @GetMapping("/editar/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Optional<EmpresaEntity> empresa = empresaService.findById(id);
        if (empresa.isPresent()) {
            model.addAttribute("empresa", empresa.get());
            
            List<BancoEntity> bancos = bancoService.findByEmpresaId(id);
            if (!bancos.isEmpty()) {
                model.addAttribute("banco", bancos.get(0));
            } else {
                model.addAttribute("banco", new BancoEntity());
            }
            
            return "empresa-form";
        }
        return "redirect:/empresas";
    }
    
    @GetMapping("/excluir/{id}")
    public String deleteEmpresa(@PathVariable Integer id) {
        try {
            
            List<BancoEntity> bancos = bancoService.findByEmpresaId(id);
            for (BancoEntity banco : bancos) {
                bancoService.deleteById(banco.getId());
            }
            
            List<PagamentoEntity> pagamentos = pagamentoService.findByEmpresaId(id);
            for (PagamentoEntity pagamento : pagamentos) {
                pagamentoService.deleteById(pagamento.getId());
            }
            
            empresaService.deleteById(id);
            return "redirect:/empresas";
            
        } catch (Exception e) {
            return "redirect:/empresas?error=delete";
        }
    }
    
    @GetMapping("/{id}/bancos")
    @ResponseBody
    public List<BancoEntity> getBancosDaEmpresa(@PathVariable Integer id) {
        return bancoService.findByEmpresaId(id);
    }
   
    
}