package br.com.fiap.megafarma.controller;

import java.util.ArrayList;

import br.com.fiap.megafarma.model.entity.Cliente;
import br.com.fiap.megafarma.model.repository.ClienteRepository;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;

@Path("/clientes")
public class ClienteResource {
    
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll() {
		ArrayList<Cliente> resposta = ClienteRepository.findAllClientes();
		ResponseBuilder response = Response.ok();
		response.entity(resposta);
		return response.build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAllById(@PathParam("id") Long id) {
		Cliente resposta = ClienteRepository.findAllByIdCliente(id);
		ResponseBuilder response = Response.ok();
		response.entity(resposta);
		return response.build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response save(@Valid Cliente cliente) {
		Cliente resposta = ClienteRepository.save(cliente);
		ResponseBuilder response = Response.created(null);
		if (resposta == null) {
			response = Response.status(400);
	}
		response.entity(resposta);
		return response.build();
	
	}
	
	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") Long id) {
		ResponseBuilder response = Response.noContent();
		if(!ClienteRepository.delete(id)) {
			response = response.status(404);
		}
		return response.build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@Valid Cliente cliente) {
		Cliente resposta = ClienteRepository.update(cliente);
		ResponseBuilder response= Response.created(null);
		if(resposta == null) {
			response.status(404);
		}
		response.entity(resposta);
		return response.build();
	}
}
