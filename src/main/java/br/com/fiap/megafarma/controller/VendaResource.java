package br.com.fiap.megafarma.controller;

import java.util.ArrayList;

import br.com.fiap.megafarma.model.entity.Venda;
import br.com.fiap.megafarma.model.repository.VendaRepository;
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

@Path("/vendas")
public class VendaResource {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll() {
		ArrayList<Venda> resposta = VendaRepository.findAllVendas();
		ResponseBuilder response = Response.ok();
		response.entity(resposta);
		return response.build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAllById(@PathParam("id") Long id) {
		Venda resposta = VendaRepository.findAllByIdVenda(id);
		ResponseBuilder response = Response.ok();
		response.entity(resposta);
		return response.build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response save(@Valid Venda venda) {
		Venda resposta = VendaRepository.save(venda);
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
		if(!VendaRepository.delete(id)) {
			response = response.status(404);
		}
		return response.build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@Valid Venda venda) {
		Venda resposta = VendaRepository.update(venda);
		ResponseBuilder response= Response.created(null);
		if(resposta == null) {
			response.status(404);
		}
		response.entity(resposta);
		return response.build();
	}
}
