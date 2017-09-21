package br.com.vita.challenge.controller.representation.territory;

import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import br.com.vita.challenge.controller.representation.ResponseEntity;
import br.com.vita.challenge.entity.Point;
import br.com.vita.challenge.entity.Territory;

/**
 * 
 * @author paulo nicezio
 *
 */
@JsonPropertyOrder({ "data", "count", "datas", "message", "errors", "error" })
public class ResponseTerritory extends ResponseEntity {

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Data data;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer count;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Collection<Data> datas;
	
	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}
	
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Collection<Data> getDatas() {
		return datas;
	}

	public void setDatas(Collection<Data> datas) {
		this.datas = datas;
	}
	
	@JsonPropertyOrder({ "id", "name", "start", "end", "area", "paintedArea", "paintedSquares" })
	public static class Data {
		
		private Long id;
		private String name;
		private Point start;
		private Point end;
		private int paintedArea;
		
		@JsonInclude(JsonInclude.Include.NON_NULL)
		private List<Point> paintedSquares;
		
		public Data() {
		}
		
		public Data(Territory territory) {
			this.id = territory.getId();
			this.name = territory.getName();
			this.start = territory.getStart();
			this.end = territory.getEnd();
			this.paintedArea = territory.getPaintedArea();
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Point getStart() {
			return start;
		}

		public void setStart(Point start) {
			this.start = start;
		}

		public Point getEnd() {
			return end;
		}

		public void setEnd(Point end) {
			this.end = end;
		}

		public int getPaintedArea() {
			return paintedArea;
		}

		public void setPaintedArea(int paintedArea) {
			this.paintedArea = paintedArea;
		}
		
		public List<Point> getPaintedSquares() {
			return paintedSquares;
		}

		public void setPaintedSquares(List<Point> paintedSquares) {
			this.paintedSquares = paintedSquares;
		}

		public Integer getArea() {
			return (end.getX() - start.getX()) * (end.getY() - start.getY());
		}
	}
	
}
