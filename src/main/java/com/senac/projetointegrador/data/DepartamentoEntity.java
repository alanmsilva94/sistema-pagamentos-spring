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
@Table(name = "Departamento")
public class DepartamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nome_depart;
    
    @OneToMany(mappedBy = "departamento")
    private List<FuncionarioEntity> funcionarios = new ArrayList<>();
}