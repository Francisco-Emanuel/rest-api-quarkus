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


@Entity
public class ItemVenda extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long codigo;

    public BigDecimal valorParcial;

    public int quantidadeParcial;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_codigo") 
    public Produto produto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venda_codigo") 
    public Venda venda;
}
