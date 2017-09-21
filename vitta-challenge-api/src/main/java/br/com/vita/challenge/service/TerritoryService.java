package br.com.vita.challenge.service;

import java.util.Collection;

import br.com.vita.challenge.entity.Territory;
import br.com.vita.challenge.exception.NotFoundException;
import br.com.vita.challenge.exception.OverlayException;

/**
 * 
 * @author paulo nicezio
 *
 */
public interface TerritoryService {
	
	Collection<Territory> getAll();
	
	Territory findBy(Long id, boolean withPainted) throws NotFoundException;
	
	void create(Territory territory) throws OverlayException;
	
	void delete(Long id) throws NotFoundException;

}
