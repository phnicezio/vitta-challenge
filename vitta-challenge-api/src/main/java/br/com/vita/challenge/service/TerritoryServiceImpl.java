package br.com.vita.challenge.service;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vita.challenge.entity.Point;
import br.com.vita.challenge.entity.Territory;
import br.com.vita.challenge.exception.NotFoundException;
import br.com.vita.challenge.exception.OverlayException;
import br.com.vita.challenge.repository.SquareRepository;
import br.com.vita.challenge.repository.TerritoryRepository;

/**
 * 
 * @author paulo nicezio
 *
 */
@Service
@Transactional
public class TerritoryServiceImpl implements TerritoryService {
	
	@Autowired
	private SquareRepository squareRepository;
	
	@Autowired
	private TerritoryRepository territoryRepository;
	
	public void create(Territory territory) throws OverlayException {
		verifyOverlay(territory);
		territoryRepository.save(territory);
	}
	
	private void verifyOverlay(Territory territory) throws OverlayException {
	    Point start = territory.getStart();
	    Point end = territory.getEnd();
	    
	    Long verifyOverlay = territoryRepository.verifyOverlay(start.getX(), start.getY(), end.getX(), end.getY());
	    
	    if (verifyOverlay > 0) { 
		   throw new OverlayException("This new territory overlays another territory.");
	   }
	}
	
	public List<Territory> getAll() {
		List<Territory> territories = territoryRepository.findAll();
		
		for (Territory territory : territories) {
			updateAmountPaintedAreas(territory);
		}
			
		return territoryRepository.findAll();
	}
	
	public Territory findBy(Long id, boolean withPainted) throws NotFoundException {
		Territory territory = territoryRepository.findOne(id);
		
		if (territory == null) {
			throw new NotFoundException("This territory was not found.");
		}

		if (withPainted) {
			Hibernate.initialize(territory.getSquares());
			if (territory.getSquares() != null) {
				territory.setPaintedArea(territory.getSquares().size());
			}
		} else {
			updateAmountPaintedAreas(territory);
		}
		
		return territory;
	}

	private void updateAmountPaintedAreas(Territory territory) {
		Integer count = countBy(territory);
		territory.setPaintedArea(count);
	}

	private Integer countBy(Territory territory) {
		return squareRepository.countBy(territory.getId());
	}
	
	public void delete(Long id) throws NotFoundException {
		boolean exists = territoryRepository.exists(id);
		if (exists) {
			territoryRepository.delete(id);
		} else {
			throw new NotFoundException("This territory was not found.");
		}
	}
	
}
