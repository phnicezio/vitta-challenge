package br.com.vita.challenge.service;

import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

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
@RunWith(MockitoJUnitRunner.class)
public class TerritoryServiceImplTest {
	
	@InjectMocks
	private TerritoryServiceImpl territoryService;
	
	@Mock
	private TerritoryRepository territoryRepository;

	@Mock
	private SquareRepository squareRepository;

	@Test
	public void createTerritory() throws OverlayException {
		Territory territory = new Territory();
		territory.setName("A");
		territory.setStart(new Point(0, 0));
		territory.setEnd(new Point(2, 2));
		
		when(territoryRepository.verifyOverlay(territory.getStart().getX(), territory.getStart().getY(),
											   territory.getEnd().getX(), territory.getEnd().getY()))
											  .thenReturn(0L);
		
		when(territoryRepository.save(territory)).thenReturn(territory);
		
		territoryService.create(territory);
		
		Assert.assertEquals("A", territory.getName());
	}
	
	@Test(expected=OverlayException.class)
	public void notCreateTerritory() throws OverlayException {
		Territory territory = new Territory();
		territory.setStart(new Point(0, 0));
		territory.setEnd(new Point(2, 2));
		
		when(territoryRepository.verifyOverlay(territory.getStart().getX(), territory.getStart().getY(),
											   territory.getEnd().getX(), territory.getEnd().getY()))
											  .thenReturn(1L);
		
		when(territoryRepository.save(territory)).thenReturn(territory);
		
		territoryService.create(territory);
	}
	
	@Test
	public void getTerritoryById() throws NotFoundException {
		Long id = 1l;
		Territory territory = new Territory();
		territory.setName("A");
		
		when(territoryRepository.findOne(id)).thenReturn(territory);
		
		Territory retrievedTerritory = territoryService.findBy(id, false);

		Assert.assertEquals("A", retrievedTerritory.getName());
	}
	
	@Test(expected=NotFoundException.class)
	public void notRetrievedTerritoryById() throws NotFoundException {
		Long id = 1l;
		Territory territory = null;
		
		when(territoryRepository.findOne(id)).thenReturn(territory);
		
		territoryService.findBy(id, false);
	}
	
	@Test
	public void deleteById() throws NotFoundException {
		Long id = 1l;
		
		when(territoryRepository.exists(id)).thenReturn(true);
		
		territoryService.delete(id);
	}
	
	@Test(expected=NotFoundException.class)
	public void notdeleteById() throws NotFoundException {
		Long id = 1l;
		
		when(territoryRepository.exists(id)).thenReturn(false);
		
		territoryService.delete(id);
	}
}
