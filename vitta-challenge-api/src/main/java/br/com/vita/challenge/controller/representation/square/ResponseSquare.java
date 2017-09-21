package br.com.vita.challenge.controller.representation.square;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import br.com.vita.challenge.controller.representation.ResponseEntity;

/**
 * 
 * @author paulo nicezio
 *
 */
@JsonPropertyOrder({ "data", "message", "errors", "error" })
public class ResponseSquare extends ResponseEntity {

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Data data;
	
	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}
	
	public static class Data {
		
		private int x, y;
		private boolean painted;

		public Data(int x, int y, boolean painted) {
			this.x = x;
			this.y = y;
			this.painted = painted;
		}

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}

		public boolean isPainted() {
			return painted;
		}

		public void setPainted(boolean painted) {
			this.painted = painted;
		}
	}

}
