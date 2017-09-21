package br.com.vita.challenge.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.vita.challenge.controller.jersey.PATCH;
import br.com.vita.challenge.controller.representation.square.ResponseSquareBuilder;
import br.com.vita.challenge.controller.representation.square.ResponseSquare;
import br.com.vita.challenge.entity.Point;
import br.com.vita.challenge.entity.Square;
import br.com.vita.challenge.exception.NotFoundException;
import br.com.vita.challenge.service.SquareService;

/**
 * 
 * @author paulo nicezio
 *
 */
@Component
@Path("/v1/squares")
public class SquareController {
	
	@Autowired
	private SquareService squareService;
	
	@GET
	@Path("/{x}/{y}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSquare(@PathParam("x") int x, @PathParam("y") int y) {
		try {
			Square square = squareService.findBy(new Point(x, y));
			ResponseSquare responseEntity = new ResponseSquareBuilder().error(false).entity(square).build();
			return Response.status(Status.OK).entity(responseEntity).build();
			
		} catch (NotFoundException e) {
			ResponseSquare response = new ResponseSquareBuilder().error(true).message("not-found").addError(e.getMessage()).build();
			return Response.status(Status.NOT_FOUND).entity(response).build();
			
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build(); 
		}
	}
	
	@PATCH
	@Path("/{x}/{y}/paint")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response paint(@PathParam("x") int x, @PathParam("y") int y) {
		try {
			Square square = squareService.paint(new Point(x, y));
			ResponseSquare response = new ResponseSquareBuilder().error(false).entity(square).build();
			return Response.status(Status.OK).entity(response).build();
			
		} catch (NotFoundException e) {
			ResponseSquare response = new ResponseSquareBuilder().error(true).message("not-found").addError(e.getMessage()).build();
			return Response.status(Status.NOT_FOUND).entity(response).build();
			
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build(); 
		}
	}
	
}
