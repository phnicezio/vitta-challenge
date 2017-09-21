package br.com.vita.challenge.controller.representation.territory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import br.com.vita.challenge.entity.Square;
import br.com.vita.challenge.entity.Territory;
import br.com.vita.challenge.controller.representation.territory.ResponseTerritory.Data;

/**
 * 
 * @author paulo nicezio
 *
 */
public class ResponseTerritoryBuilder {

	private ResponseTerritory responseEntity = new ResponseTerritory();
	
	public ResponseTerritoryBuilder error(boolean error) {
		responseEntity.setError(error);
		return this;
	}
	
	public ResponseTerritoryBuilder entity(Territory territory) {
		responseEntity.setData(new Data(territory));
		return this;
	}
	
	public ResponseTerritoryBuilder entityWithSquares(Territory territory, boolean withPainted) {
		Data data = new Data(territory);
		if (withPainted && territory.getSquares() != null) {
			data.setPaintedSquares(territory.getSquares().stream().map(Square::getPoint).collect(Collectors.toList()));
		}
		responseEntity.setData(data);
		return this;
	}
	
	public ResponseTerritoryBuilder count(Integer count) {
		responseEntity.setCount(count);
		return this;
	}
	
	public ResponseTerritoryBuilder list(Collection<Territory> territories) {
		List<Data> datas = new ArrayList<>(); 
		for (Territory territory : territories) {
			datas.add(new Data(territory));
		}
		responseEntity.setDatas(datas);
		return this;
	}
	
	public ResponseTerritoryBuilder message(String message) {
		responseEntity.setMessage(message);
		return this;
	}
	
	public ResponseTerritoryBuilder addError(String error) {
		responseEntity.initErrors();
		responseEntity.getErrors().add(error);
		return this;
	}
	
	public ResponseTerritory build() {
		return responseEntity;
	}
}
