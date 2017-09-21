package br.com.vita.challenge.service;

import br.com.vita.challenge.entity.Point;
import br.com.vita.challenge.entity.Square;
import br.com.vita.challenge.exception.NotFoundException;

public interface SquareService {

	Square findBy(Point point) throws NotFoundException;
	
	Square paint(Point point) throws NotFoundException;
	
}
