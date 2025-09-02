package com.restapi.domain.entidade;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Produto extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long codigo;

    public String nome;
    
    public BigDecimal preco;
    
    public int quantidade;
    
    public LocalDate validade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_codigo")
    public Categoria categoria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fabricante_codigo")
    public Fabricante fabricante;
}
