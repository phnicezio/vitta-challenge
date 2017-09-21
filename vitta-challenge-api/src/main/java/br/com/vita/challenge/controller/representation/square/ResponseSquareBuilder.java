package br.com.vita.challenge.controller.representation.square;

import br.com.vita.challenge.controller.representation.square.ResponseSquare.Data;
import br.com.vita.challenge.entity.Square;

/**
 * 
 * @author paulo nicezio
 *
 */
public class ResponseSquareBuilder {

	private ResponseSquare response = new ResponseSquare();
	
	public ResponseSquareBuilder error(boolean error) {
		response.setError(error);
		return this;
	}
	
	public ResponseSquareBuilder entity(Square square) {
		Data data = new Data(square.getPoint().getX(), square.getPoint().getY(), square.getPainted());
		response.setData(data);
		return this;
	}
	
	public ResponseSquareBuilder message(String message) {
		response.setMessage(message);
		return this;
	}
	
	public ResponseSquareBuilder addError(String error) {
		response.initErrors();
		response.getErrors().add(error);
		return this;
	}
	
	public ResponseSquare build() {
		return response;
	}
}
