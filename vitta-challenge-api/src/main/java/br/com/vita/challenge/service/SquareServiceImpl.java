package br.com.vita.challenge.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vita.challenge.entity.Point;
import br.com.vita.challenge.entity.Square;
import br.com.vita.challenge.entity.Territory;
import br.com.vita.challenge.exception.NotFoundException;
import br.com.vita.challenge.repository.SquareRepository;
import br.com.vita.challenge.repository.TerritoryRepository;

@Service
public class SquareServiceImpl implements SquareService {

	@Autowired
	private SquareRepository squareRepository;
	
	@Autowired
	private TerritoryRepository territoryRepository;

	@Override
	public Square findBy(Point point) throws NotFoundException {
		Optional<Square> squareOptional = squareRepository.findByPoint(point);
		
		if (squareOptional.isPresent()) {
			Square square = squareOptional.get();
			square.setPainted(false);
			return square;
		}
		
		Optional<Territory> territoryOptional = findTerritoryBy(point);
		if (territoryOptional.isPresent()) {
			Square square = new Square();
			square.setPoint(point);
			square.setPainted(false);
			return square;
		}
		
		throw new NotFoundException("This square does not belong to any territory.");
	}
	
	public Square paint(Point point) throws NotFoundException {
		Optional<Territory> territory = findTerritoryBy(point);
		
		if (territory.isPresent()) {
			return persistSquare(point, territory);
		}
		
		throw new NotFoundException("This square does not belong to any territory.");
	}

	private Optional<Territory> findTerritoryBy(Point point) {
		return territoryRepository.findByPoint(point.getX(), point.getY());
	}

	@Transactional
	private Square persistSquare(Point point, Optional<Territory> territory) {
		Square square = new Square();
		square.setPoint(point);
		square.setTerritory(territory.get());
		square.setPainted(true);
		return squareRepository.save(square);
	}
	
}
