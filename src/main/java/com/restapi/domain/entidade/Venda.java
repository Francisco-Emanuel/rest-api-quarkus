package com.restapi.domain.entidade;

import java.math.BigInteger;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Venda extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long codigo;

    @CreationTimestamp
    public LocalDateTime dataHora;

    public BigInteger valorTotal;

    public Integer quantidadeTotal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_codigo") 
    public Cliente cliente;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funcionario_codigo")
    public Funcionario funcionario;

}
