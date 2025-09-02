package com.restapi.domain.aplicacao;

import java.util.UUID;
import java.net.URI;
import java.util.List;

import com.restapi.domain.entidade.Pessoa;
import com.restapi.domain.repositorio.RepositorioPessoa;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;

@Path("/pessoas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ResourcePessoa {

    // @Inject
    // public RepositorioPessoa repositoriopessoa;

    @POST
    @Transactional
    public Response criarPessoa(Pessoa pessoa) {
        pessoa.persist();

        URI location = UriBuilder.fromResource(ResourcePessoa.class).path("{id}").build(pessoa.id);
        return Response.created(location).entity(pessoa).build();
    }

    @GET
    @Path("/{id}")
    public Pessoa buscarPessoaPorId(@PathParam("id") UUID id) {
        Pessoa pessoa = (Pessoa) Pessoa.findById(id);

        if (pessoa == null) {
            throw new NotFoundException("Pessoa com ID " + id + " não encontrada.");
        }

        return pessoa;
    }

    @GET
    public List<Pessoa> listarPessoas(
            @QueryParam("page") @DefaultValue("0") int pageIndex,
            @QueryParam("size") @DefaultValue("10") int pageSize) {
        return Pessoa.findAll().page(pageIndex, pageSize).list();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Pessoa atualizarPessoa(@PathParam("id") UUID id, Pessoa pessoaAtualizada) {
        Pessoa pessoaExistente = Pessoa.findByIdOptional(id)
                .map(entity -> (Pessoa) entity) // <-- Ajuste aplicado aqui!
                .orElseThrow(() -> new NotFoundException("Pessoa com ID " + id + " não encontrada para atualização."));

        pessoaExistente.name = pessoaAtualizada.name;
        pessoaExistente.email = pessoaAtualizada.email;

        return pessoaExistente;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deletarPessoa(@PathParam("id") UUID id) {
        boolean deletado = Pessoa.deleteById(id);
        if (deletado) {
            return Response.noContent().build();
        }
        throw new NotFoundException("Pessoa com ID " + id + " não encontrada para exclusão.");
    }

}
