package com.senac.projetointegrador.data;

import jakarta.persistence.Entity; 
import jakarta.persistence.GeneratedValue; 
import jakarta.persistence.GenerationType; 
import jakarta.persistence.Id; 
import jakarta.persistence.Table; 
import lombok.Data; 

@Data 
@Entity 
@Table(name="Documento") 
public class DocContabilEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String tipoDocumento;
    private String numeroDocumento;
    private String dataEmissao;
    private double valor;
    private String descricao;

}
