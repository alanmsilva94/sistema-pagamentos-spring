package com.senac.projetointegrador.data;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
import java.util.ArrayList;

@Data
@Entity
@Table(name = "Funcionario")
public class FuncionarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nome;
    private String email;
    private String telefone;
    
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "departamento_id")
    private DepartamentoEntity departamento;

    @OneToMany(mappedBy = "funcionario")
    private List<PagamentoEntity> pagamentos = new ArrayList<>();
}