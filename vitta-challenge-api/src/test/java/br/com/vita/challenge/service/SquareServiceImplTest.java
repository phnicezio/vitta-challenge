package br.com.vita.challenge.service;

import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.vita.challenge.entity.Point;
import br.com.vita.challenge.entity.Square;
import br.com.vita.challenge.entity.Territory;
import br.com.vita.challenge.exception.NotFoundException;
import br.com.vita.challenge.repository.SquareRepository;
import br.com.vita.challenge.repository.TerritoryRepository;

/**
 * 
 * @author paulo nicezio
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class SquareServiceImplTest {

	@InjectMocks
	private SquareServiceImpl squareService;
	
	@Mock
	private TerritoryRepository territoryRepository;

	@Mock
	private SquareRepository squareRepository;
	
	@Test
	public void findSquare() throws NotFoundException {
		Point point = new Point(1, 2);
		
		when(squareRepository.findByPoint(point)).thenReturn(Optional.ofNullable(new Square()));
		
		Square square = squareService.findBy(point);
		
		Assert.assertFalse(square.getPainted());
	}
	
	@Test
	public void findTerritory() throws NotFoundException {
		Point point = new Point(1, 2);
		
		when(squareRepository.findByPoint(point)).thenReturn(Optional.ofNullable(null));
		
		when(territoryRepository.findByPoint(point.getX(), point.getY())).thenReturn(Optional.ofNullable(new Territory()));
		
		Square square = squareService.findBy(point);
		
		Assert.assertEquals(1, square.getPoint().getX());
		Assert.assertEquals(2, square.getPoint().getY());
		Assert.assertFalse(square.getPainted());
	}
	
	@Test(expected=NotFoundException.class)
	public void notFoundSquare() throws NotFoundException {
		Point point = new Point(1, 2);
		
		when(squareRepository.findByPoint(point)).thenReturn(Optional.ofNullable(null));
		
		when(territoryRepository.findByPoint(point.getX(), point.getY())).thenReturn(Optional.ofNullable(null));
		
		squareService.findBy(point);
	}
	
	@Test
	public void paint() throws NotFoundException {
		Point point = new Point(1, 2);

		Square square = new Square();
		square.setPoint(point);
		
		when(territoryRepository.findByPoint(point.getX(), point.getY())).thenReturn(Optional.ofNullable(new Territory()));
		
		when(squareRepository.save(square)).thenReturn(square);
		
		squareService.paint(point);
		
		Assert.assertEquals(1, square.getPoint().getX());
		Assert.assertEquals(2, square.getPoint().getY());
	}
	
	@Test(expected=NotFoundException.class)
	public void notPainted() throws NotFoundException {
		Point point = new Point(1, 2);

		Square square = new Square();
		square.setPoint(point);
		
		when(territoryRepository.findByPoint(point.getX(), point.getY())).thenReturn(Optional.ofNullable(null));
		
		squareService.paint(point);
	}
	
}
