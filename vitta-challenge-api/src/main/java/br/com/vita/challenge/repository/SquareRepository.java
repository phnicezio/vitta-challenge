package br.com.vita.challenge.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.vita.challenge.entity.Point;
import br.com.vita.challenge.entity.Square;

/**
 * 
 * @author paulo nicezio
 *
 */
@Repository
public interface SquareRepository extends JpaRepository<Square, Long> {

	Optional<Square> findByPoint(Point point);
	
	@Query("SELECT COUNT(s.id) FROM Square s JOIN s.territory t WHERE t.id = :idTerritory")
	Integer countBy(@Param("idTerritory") Long idTerritory);

}
