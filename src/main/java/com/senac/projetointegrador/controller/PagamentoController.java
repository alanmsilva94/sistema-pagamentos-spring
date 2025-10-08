package com.senac.projetointegrador.controller;

import com.senac.projetointegrador.data.PagamentoEntity;
import com.senac.projetointegrador.data.EmpresaEntity;
import com.senac.projetointegrador.data.FuncionarioEntity;
import com.senac.projetointegrador.data.DepartamentoEntity;
import com.senac.projetointegrador.service.PagamentoService;
import com.senac.projetointegrador.service.EmpresaService;
import com.senac.projetointegrador.service.FuncionarioService;
import com.senac.projetointegrador.service.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/pagamentos")
public class PagamentoController {
    
    @Autowired
    private PagamentoService pagamentoService;
    
    @Autowired
    private EmpresaService empresaService;
    
    @Autowired
    private FuncionarioService funcionarioService;
    
    @Autowired
    private DepartamentoService departamentoService;
    
    @GetMapping
    public String listPagamentos(Model model) {
        List<PagamentoEntity> pagamentos = pagamentoService.findAll();
        model.addAttribute("pagamentos", pagamentos);
        return "pagamentos";
    }
    
    @GetMapping("/novo")
    public String showPagamentoForm(Model model) {
        model.addAttribute("pagamento", new PagamentoEntity());
        model.addAttribute("empresas", empresaService.findAll());
        model.addAttribute("funcionarios", funcionarioService.findAll());
        model.addAttribute("departamentos", departamentoService.findAll());
        return "pagamento-form";
    }
    
    @PostMapping("/salvar")
    public String savePagamento(@ModelAttribute PagamentoEntity pagamento,
                               @RequestParam Integer empresaId,
                               @RequestParam(required = false) Integer funcionarioId) {
        
        if (empresaId != null) {
            empresaService.findById(empresaId).ifPresent(empresa -> {
                pagamento.setEmpresa(empresa);
            });
        }
        
        if (funcionarioId != null) {
            funcionarioService.findById(funcionarioId).ifPresent(pagamento::setFuncionario);
        }
        
        pagamentoService.save(pagamento);
        return "redirect:/pagamentos";
    }
    
    @GetMapping("/editar/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Optional<PagamentoEntity> pagamento = pagamentoService.findById(id);
        if (pagamento.isPresent()) {
            PagamentoEntity pagamentoEntity = pagamento.get();
            model.addAttribute("pagamento", pagamentoEntity);
            model.addAttribute("empresas", empresaService.findAll());
            model.addAttribute("funcionarios", funcionarioService.findAll());
            model.addAttribute("departamentos", departamentoService.findAll());
            
            if (pagamentoEntity.getFuncionario() != null && pagamentoEntity.getFuncionario().getDepartamento() != null) {
                model.addAttribute("departamentoSelecionado", pagamentoEntity.getFuncionario().getDepartamento().getId());
            }
            
            return "pagamento-form";
        }
        return "redirect:/pagamentos";
    }
    
    @GetMapping("/excluir/{id}")
    public String deletePagamento(@PathVariable Integer id) {
        pagamentoService.deleteById(id);
        return "redirect:/pagamentos";
    }
    
    @GetMapping("/api/funcionarios/{departamentoId}")
    @ResponseBody
    public List<FuncionarioEntity> getFuncionariosPorDepartamento(@PathVariable Integer departamentoId) {
        return funcionarioService.findByDepartamentoId(departamentoId);
    }
}