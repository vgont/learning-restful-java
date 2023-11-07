/* VINICIUS GONTIJO SANTOS
 * RM550657
 * */

package br.com.fiap.megafarma.controller;

import java.util.ArrayList;

import br.com.fiap.megafarma.model.entity.ItemVendido;
import br.com.fiap.megafarma.model.repository.ItemVendidoRepository;
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

@Path("itens")
public class ItemVendidoResource {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll() {
		ArrayList<ItemVendido> resposta = ItemVendidoRepository.findAllItensVendidos();
		ResponseBuilder response = Response.ok();
		response.entity(resposta);
		return response.build();
	}
	
	@GET
	@Path("/{idVenda}/{idRemedio}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAllById(@PathParam("idVenda") Long idVenda, @PathParam("idRemedio") Long idRemedio) {
		ItemVendido resposta = ItemVendidoRepository.findAllByIdVendaAndRemedio(idVenda, idRemedio);
		ResponseBuilder response = Response.ok();
		response.entity(resposta);
		return response.build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response save(@Valid ItemVendido item) {
		ItemVendido resposta = ItemVendidoRepository.save(item);
		ResponseBuilder response = Response.created(null);
		if (resposta == null) {
			response = Response.status(400);
	}
		response.entity(resposta);
		return response.build();
	
	}
	
	@DELETE
	@Path("/{idVenda}/{idRemedio}")
	public Response delete(@PathParam("idVenda") Long idVenda, @PathParam("idRemedio") Long idRemedio) {
		ResponseBuilder response = Response.noContent();
		if(!ItemVendidoRepository.delete(idVenda, idRemedio)) {
			response = response.status(404);
		}
		return response.build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@Valid ItemVendido item) {
		ItemVendido resposta = ItemVendidoRepository.update(item);
		ResponseBuilder response= Response.created(null);
		if(resposta == null) {
			response.status(404);
		}
		response.entity(resposta);
		return response.build();
	}
}
