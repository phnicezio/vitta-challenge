package br.com.vita.challenge.controller.representation;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 
 * @author paulo nicezio
 *
 */
public class ResponseEntity {

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Boolean error;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String message;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<String> errors;

	public Boolean isError() {
		return error;
	}

	public void setError(Boolean error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	
	public void initErrors() {
		if (this.getErrors() == null) {
			this.setErrors(new ArrayList<>());
		}
	}

}
