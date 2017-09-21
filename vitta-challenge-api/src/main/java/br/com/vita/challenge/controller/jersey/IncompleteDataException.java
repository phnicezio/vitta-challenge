package br.com.vita.challenge.controller.jersey;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import br.com.vita.challenge.controller.representation.territory.ResponseTerritoryBuilder;
import br.com.vita.challenge.controller.representation.territory.ResponseTerritory;

@Provider
public class IncompleteDataException implements ExceptionMapper<javax.validation.ValidationException> {

	@Override
	public Response toResponse(javax.validation.ValidationException e) {
		ResponseTerritoryBuilder responseBuilder = new ResponseTerritoryBuilder();
		
		((ConstraintViolationException) e).getConstraintViolations().forEach(cv -> responseBuilder.addError(cv.getMessage()));

		ResponseTerritory response = responseBuilder.error(true).message("incomplete-data").build();
		
		return Response.status(Status.BAD_REQUEST).entity(response).build();
	}
}