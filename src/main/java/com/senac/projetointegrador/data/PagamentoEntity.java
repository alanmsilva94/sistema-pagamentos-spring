package com.senac.projetointegrador.data;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "Pagamento")
public class PagamentoEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id; 
    private LocalDate dataPagamento;
    private double valor;
    private String codigoBoleto;
    
    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private EmpresaEntity empresa;
    
    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private FuncionarioEntity funcionario;
}