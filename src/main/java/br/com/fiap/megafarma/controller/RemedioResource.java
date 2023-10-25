package br.com.fiap.megafarma.controller;

import java.util.ArrayList;

import br.com.fiap.megafarma.model.entity.Remedio;
import br.com.fiap.megafarma.model.repository.RemedioRepository;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;

@Path("/megafarma")
public class RemedioResource {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll() {
		ArrayList<Remedio> resposta = RemedioRepository.findAllRemedio();
		ResponseBuilder response = Response.ok();
		response.entity(resposta);
		return response.build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response save(@Valid Remedio remedio) {
		Remedio resposta = RemedioRepository.save(remedio);
		ResponseBuilder response = Response.created(null);
		if (resposta == null) {
			response = Response.status(400);
	}
		response.entity(resposta);
		return response.build();
	
	}
}

