package com.senac.projetointegrador.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.OneToMany;
import java.util.List;
import java.util.ArrayList;
import lombok.Data;

@Data
@Entity
@Table(name = "Empresa")
public class EmpresaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nome;
    private String cnpj;
    private String uf;
    private String cidade;
    private String endereco;
    private String telefone;
    private String email;

    @OneToMany(mappedBy = "empresa")
    private List<BancoEntity> bancos = new ArrayList<>();

    @OneToMany(mappedBy = "empresa")
    private List<PagamentoEntity> pagamentos = new ArrayList<>();
}