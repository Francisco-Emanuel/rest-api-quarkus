package com.restapi.domain.repositorio;

import java.util.UUID;

import com.restapi.domain.entidade.Pessoa;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.inject.Singleton;

@Singleton
public class RepositorioPessoa implements PanacheRepositoryBase<Pessoa, UUID> {
    
}

/*
 * APENAS SE EU DECIDIR USAR GETTERS E SETTERS, O QUE POR AGORA NÃO ACHO QUE SEJA NECESSÁRIO
 */
