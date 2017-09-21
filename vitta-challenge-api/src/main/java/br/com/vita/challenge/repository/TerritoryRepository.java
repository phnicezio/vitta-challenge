package br.com.vita.challenge.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.vita.challenge.entity.Territory;

/**
 * 
 * @author paulo nicezio
 *
 */
@Repository
public interface TerritoryRepository extends JpaRepository<Territory, Long> {

	@Query("SELECT COUNT(t) FROM Territory t WHERE "
			+ "(:startX < (t.start.x + (t.end.x - t.start.x))) AND "
			+ "(:startY < (t.start.y + (t.end.y - t.start.y))) AND "
			+ "(t.start.x < (:startX + (:endX - :startX))) AND "
			+ "(t.start.y < (:startY + (:endY - :startY)))")
	Long verifyOverlay(@Param("startX") Integer startX, 
			   @Param("startY") Integer startY, 
			   @Param("endX") Integer endX, 
			   @Param("endY") Integer endY);
	
	@Query("SELECT t FROM Territory t WHERE :x >= t.start.x AND :x <= t.end.x AND :y >= t.start.y AND :y <= t.end.y")
	Optional<Territory> findByPoint(@Param("x") Integer x, @Param("y") Integer y);

}
