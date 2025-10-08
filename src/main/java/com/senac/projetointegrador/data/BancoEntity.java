package com.senac.projetointegrador.data;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Banco")
public class BancoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer codigoBanco;
    private String nomeBanco;
    private String tipoConta;
    private Integer agencia;
    private Integer digitoAgencia;
    private Integer conta;
    private Integer digitoConta;
    private String chavePix;
    private String nomeRecebedor;
    private String cnpjRecebedor;
    
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "empresa_id")
    private EmpresaEntity empresa;
}