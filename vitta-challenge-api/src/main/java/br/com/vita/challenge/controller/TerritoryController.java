package br.com.vita.challenge.controller;

import java.net.URI;
import java.util.Collection;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.vita.challenge.controller.representation.territory.ResponseTerritoryBuilder;
import br.com.vita.challenge.controller.representation.territory.ResponseTerritory;
import br.com.vita.challenge.entity.Territory;
import br.com.vita.challenge.exception.NotFoundException;
import br.com.vita.challenge.exception.OverlayException;
import br.com.vita.challenge.service.TerritoryService;

/**
 * 
 * @author paulo nicezio
 *
 */
@Component
@Path("/v1/territories")
public class TerritoryController {

	private static final Logger log = LoggerFactory.getLogger(TerritoryController.class);

	private static final String NOT_FOUND = "not-found";
	
	@Context
	private UriInfo uriInfo;
	
	@Autowired
	private TerritoryService territoryService;
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(@Valid Territory territory) {
		log.info("Creating new territory: ", territory);
		
		try {
			territoryService.create(territory);
			
			URI uriCreate = URI.create(uriInfo.getPath() + "/" + territory.getId());
			
			ResponseTerritory response = new ResponseTerritoryBuilder().error(false).entity(territory).build();
			
			log.info("Created new territory with success: ", territory);
	        return Response.status(Status.CREATED).entity(response).contentLocation(uriCreate).build();
			
		} catch (OverlayException e) {
			ResponseTerritory response = new ResponseTerritoryBuilder().error(true).message("territory-overlay").addError(e.getMessage()).build(); 
			return Response.status(Status.CONFLICT).entity(response).build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseTerritory getAll() {
		Collection<Territory> territories = territoryService.getAll();
		log.info("GetAllTerritories returned: ", territories.size());
		return new ResponseTerritoryBuilder().count(territories.size()).list(territories).build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTerritory(@PathParam("id") long id) {
		try {
			Territory territory = territoryService.findBy(id, false);
			ResponseTerritory response = new ResponseTerritoryBuilder().error(false).entity(territory).build(); 
			return Response.status(Status.OK).entity(response).build();
			
		} catch (NotFoundException e) {
			ResponseTerritory response = new ResponseTerritoryBuilder().error(true).message(NOT_FOUND).addError(e.getMessage()).build();
			return Response.status(Status.NOT_FOUND).entity(response).build();
			
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GET
	@Path("/territory/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response paintedSquaresByTerritory(@PathParam("id") long id, @QueryParam("withpainted") boolean withpainted) {
		try {
			Territory territory = territoryService.findBy(id, withpainted);
			ResponseTerritory response = new ResponseTerritoryBuilder().error(false).entityWithSquares(territory, withpainted).build(); 
			return Response.status(Status.OK).entity(response).build();
			
		} catch (NotFoundException e) {
			ResponseTerritory response = new ResponseTerritoryBuilder().error(true).message(NOT_FOUND).addError(e.getMessage()).build();
			return Response.status(Status.NOT_FOUND).entity(response).build();
			
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("id") long id) {
		try {
			territoryService.delete(id);
			ResponseTerritory response = new ResponseTerritoryBuilder().error(false).build(); 
			return Response.status(Status.OK).entity(response).build();
			
		} catch (NotFoundException e) {
			ResponseTerritory response = new ResponseTerritoryBuilder().error(true).message(NOT_FOUND).addError(e.getMessage()).build();
			return Response.status(Status.NOT_FOUND).entity(response).build();
			
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

}
